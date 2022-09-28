package dev.emad.framework.route

import dev.emad.framework.data.remote.request.model.LoginRequest
import dev.emad.framework.data.remote.request.model.UserRequest
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.userRoutes() {
    loginRoute()
    getAllUsersRoute()
    createUser()
}

fun Routing.loginRoute() {
    val userService: dev.emad.business.service.UserService by inject()

    post("/login") {
        val request = call.receive<LoginRequest>()
        val response = userService.loginRespond(request.username, request.password)
        call.respond(response.status, response.data)
    }
}

fun Routing.getAllUsersRoute() {
    val userService: dev.emad.business.service.UserService by inject()

    authenticate("auth-admin") {
        get("/user") {
            val response = userService.getAllUsersRespond()
            call.respond(response.status, response.data)
        }
    }
}

fun Routing.createUser() {
    val userService: dev.emad.business.service.UserService by inject()

    authenticate("auth-admin") {
        post("/user/create") {
            val request = call.receive<UserRequest>()
            val response = userService.createUserRespond(request)
            call.respond(response.status, response.data)
        }
    }
}