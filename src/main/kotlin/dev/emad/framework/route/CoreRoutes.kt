package dev.emad.framework.route

import dev.emad.business.service.SearchService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.json.simple.JSONObject
import org.koin.ktor.ext.inject

fun Routing.coreRoutes() {
    rootRoute()
    adminRoute()
    searchRoute()
}

fun Routing.rootRoute() {
    authenticate("auth-customer") {
        get("/") {
            val principal = call.principal<JWTPrincipal>()
            val username = principal!!.payload.getClaim("username").asString()
            val isAdmin = principal.payload.getClaim("isAdmin").asBoolean()
            val expiresAt = principal.expiresAt?.time?.minus(System.currentTimeMillis())
            val message = "Hello, $username! Token is expired at $expiresAt ms. " +
                    "You are ${if (!isAdmin) "not" else ""} an admin."
            call.respond(
                HttpStatusCode.OK,
                JSONObject(mapOf("message" to message))
            )
        }
    }
}

fun Routing.adminRoute() {
    authenticate("auth-admin") {
        get("/admin") {
            call.respond(
                HttpStatusCode.OK,
                JSONObject(mapOf("message" to "Hello admin!"))
            )
        }
    }
}

fun Routing.searchRoute() {
    val searchService: SearchService by inject()

    authenticate("auth-customer") {
        get("/search") {
            val query = call.request.queryParameters["query"]
            val response = searchService.search(query)
            call.respond(
                response.status,
                response.data
            )
        }
    }
}