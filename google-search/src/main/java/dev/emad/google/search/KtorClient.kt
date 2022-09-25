package dev.emad.google.search

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.gson.*

object KtorClient {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }
}