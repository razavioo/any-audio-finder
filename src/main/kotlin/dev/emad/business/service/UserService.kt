package dev.emad.business.service

import dev.emad.framework.data.remote.request.model.UserRequest
import dev.emad.framework.data.remote.response.model.Response

interface UserService {
    fun loginRespond(username: String, password: String): Response
    fun getAllUsersRespond(): Response
    fun createUserRespond(userRequest: UserRequest): Response
}