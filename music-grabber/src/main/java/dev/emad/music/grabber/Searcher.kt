package dev.emad.music.grabber

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class Searcher {
    fun search(query: String): Flow<List<SearchResult>> = flow {
        val searchUrl = "https://www.google.com/search?q=$query"
        val results = fetchSearchResultsFromGoogle(searchUrl)
        emit(results)
    }

    private fun fetchSearchResultsFromGoogle(searchUrl: String): List<SearchResult> {
        val results = mutableListOf<SearchResult>()

        try {
            val doc: Document = Jsoup.connect(searchUrl)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
                .get()

            val elements = doc.select("div.tF2Cxc")
            for (element in elements) {
                val titleElement = element.selectFirst("h3")
                val linkElement = element.selectFirst("a")

                val title = titleElement?.text()
                val url = linkElement?.absUrl("href")

                if (title != null && url != null) {
                    results.add(SearchResult(title, url))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return results
    }
}