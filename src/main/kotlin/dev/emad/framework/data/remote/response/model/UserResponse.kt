package dev.emad.framework.data.remote.response.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: Long? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val username: String,
    val password: String,
    val isAdmin: Boolean = false
)