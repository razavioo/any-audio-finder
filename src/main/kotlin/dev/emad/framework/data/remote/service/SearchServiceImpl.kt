package dev.emad.framework.data.remote.service

import dev.emad.business.service.SearchService
import dev.emad.framework.data.remote.response.model.Response
import dev.emad.google.search.SearchResult
import dev.emad.google.search.Searcher
import dev.emad.music.grabber.GeneralMusicGrabber
import dev.emad.music.grabber.MusicGrabber
import io.ktor.http.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withTimeoutOrNull
import org.json.simple.JSONObject

class SearchServiceImpl : SearchService {
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

            searchResults?.let {
                val musicGrabber: MusicGrabber = GeneralMusicGrabber()
                val downloadUrl = it.first()
                val musicInformation = withTimeoutOrNull(MAX_MUSIC_GRABBER_DELAY) {
                    musicGrabber.grab(downloadUrl.url).first()
                }

                musicInformation?.let {
                    Response(
                        JSONObject(mapOf("message" to musicInformation)),
                        HttpStatusCode.OK
                    )
                } ?: run {
                    Response(
                        JSONObject(mapOf("message" to "No search result found")),
                        HttpStatusCode.NotFound
                    )
                }
            } ?: run {
                Response(
                    JSONObject(mapOf("message" to "Search results not received")),
                    HttpStatusCode.Forbidden
                )
            }
        }
    }

    companion object {
        const val MAX_SEARCH_RESULT_DELAY = 20_000L
        const val MAX_MUSIC_GRABBER_DELAY = 20_000L
    }
}