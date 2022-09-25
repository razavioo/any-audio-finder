package dev.emad.framework.data.remote.service

import dev.emad.business.service.SearchService
import dev.emad.framework.data.remote.response.model.Response
import dev.emad.google.search.SearchResult
import dev.emad.google.search.Searcher
import io.ktor.http.*
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
                Searcher().search(query).firstOrNull()
            }

            searchResults?.let {
                Response(
                    JSONObject(mapOf("message" to searchResults)),
                    HttpStatusCode.OK
                )
            } ?: run {
                Response(
                    JSONObject(mapOf("message" to "Search results not received")),
                    HttpStatusCode.Forbidden
                )
            }
        }
    }

    companion object {
        const val MAX_SEARCH_RESULT_DELAY = 200000L
    }
}