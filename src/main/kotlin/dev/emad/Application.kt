package dev.emad

import dev.emad.plugins.configureHTTP
import dev.emad.plugins.configureRouting
import dev.emad.plugins.configureSecurity
import dev.emad.plugins.configureSerialization
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    val port = System.getenv("PORT").toIntOrNull() ?: 8080
    val host = System.getenv("HOST") ?: "0.0.0.0"
    embeddedServer(Netty, port = port, host = host) {
        configureSerialization()
        configureHTTP()
        configureSecurity()
        configureRouting()
    }.start(wait = true)
}
