package dev.emad.framework.data.remote.response.model

import io.ktor.http.*

data class Response(
    val data: Any,
    val status: HttpStatusCode
)