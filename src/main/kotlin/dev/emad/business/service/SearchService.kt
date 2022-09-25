package dev.emad.business.service

import dev.emad.framework.data.remote.response.model.Response

interface SearchService {
    suspend fun search(query: String?): Response
}