package dev.emad.framework.data.remote.service

import dev.emad.business.model.Audio
import dev.emad.business.service.SearchService
import dev.emad.framework.data.remote.response.mapper.AudioResponseMapper
import dev.emad.framework.data.remote.response.model.AudioResponse
import dev.emad.framework.data.remote.response.model.Response
import dev.emad.framework.data.repository.AudioRepository
import dev.emad.google.search.SearchResult
import dev.emad.google.search.Searcher
import dev.emad.music.grabber.GeneralMusicGrabber
import dev.emad.music.grabber.MusicGrabber
import dev.emad.utils.EncryptionUtils
import io.ktor.http.*
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withTimeoutOrNull
import org.json.simple.JSONObject
import java.util.*

class SearchServiceImpl(
    private val audioRepository: AudioRepository,
    private val audioResponseMapper: AudioResponseMapper = AudioResponseMapper()
) : SearchService {
    override suspend fun search(query: String?): Response {
        return if (query == null) {
            Response(
                JSONObject(mapOf("message" to "Search query not found!")),
                HttpStatusCode.BadRequest
            )
        } else {
            val searchResults: List<SearchResult>? = withTimeoutOrNull(MAX_SEARCH_RESULT_DELAY) {
                try {
                    Searcher().search(query).firstOrNull()
                } catch (exception: Exception) {
                    println(exception.message)
                    null
                }
            }

            return if (searchResults == null) {
                Response(
                    JSONObject(mapOf("message" to "Search results not received")),
                    HttpStatusCode.Forbidden
                )
            } else {
                val musicGrabber: MusicGrabber = GeneralMusicGrabber()
                val responses = mutableListOf<AudioResponse>()

                searchResults.forEach { searchResult ->
                    val insertedAudio = audioRepository.getFirstFromPageUrl(searchResult.url)
                    if (insertedAudio != null) {
                        val audioResponse = audioResponseMapper.from(insertedAudio)
                        responses.add(audioResponse)
                    } else {
                        val musicInformation = withTimeoutOrNull(MAX_MUSIC_GRABBER_DELAY) {
                            musicGrabber.grab(searchResult.url).firstOrNull()
                        }

                        musicInformation?.let {
                            val redirectionId = EncryptionUtils.md5(musicInformation.downloadUrl)
                            val audio = Audio(
                                pageUrl = musicInformation.pageUrl,
                                downloadUrl = musicInformation.downloadUrl,
                                redirectionId = redirectionId,
                                createdAt = Date()
                            )
                            val insertId = audioRepository.insert(audio)
                            audio.id = insertId

                            val audioResponse = audioResponseMapper.from(audio)
                            responses.add(audioResponse)
                        }
                    }
                }

                return if (responses.isEmpty()) {
                    Response(
                        JSONObject(mapOf("message" to "No search result found")),
                        HttpStatusCode.NotFound
                    )
                } else {
                    Response(
                        JSONObject(mapOf("message" to responses)),
                        HttpStatusCode.OK
                    )
                }
            }
        }
    }

    companion object {
        const val MAX_SEARCH_RESULT_DELAY = 20_000L
        const val MAX_MUSIC_GRABBER_DELAY = 10_000L
    }
}