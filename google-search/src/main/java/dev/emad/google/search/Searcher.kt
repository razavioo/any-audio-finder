package dev.emad.google.search

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class Searcher {
    fun search(query: String): Flow<List<SearchResult>> = callbackFlow {
        val customSearchUrl = "https://www.googleapis.com/customsearch"
        val customSearchEngineId = "6658fae35a8394a8e"
        val googleSearchApiKey = System.getenv("GOOGLE_SEARCH_API_KEY") ?: return@callbackFlow
        val url = "$customSearchUrl/v1?q=$query&key=$googleSearchApiKey&cx=$customSearchEngineId"
        val response = KtorClient.client.get(url)
        if (response.status == HttpStatusCode.OK) {
            val body = response.body<SearchResponse>()
            val searchResults: List<SearchResult> = body.items.map {
                SearchResult(it.title, it.htmlFormattedURL)
            }
            trySend(searchResults)
        } else {
            close()
        }

        awaitClose()
    }
}