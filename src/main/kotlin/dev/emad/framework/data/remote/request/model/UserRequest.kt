package dev.emad.framework.data.remote.request.model

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    val firstName: String,
    val lastName: String,
    val username: String,
    val password: String,
    val isAdmin: Boolean
)