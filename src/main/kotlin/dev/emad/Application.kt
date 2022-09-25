package dev.emad

import dev.emad.plugins.configureHTTP
import dev.emad.plugins.configureRouting
import dev.emad.plugins.configureSecurity
import dev.emad.plugins.configureSerialization
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureSerialization()
        configureHTTP()
        configureSecurity()
        configureRouting()
    }.start(wait = true)
}
