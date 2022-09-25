package dev.emad.business.model

data class User(
    val id: Long? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val username: String,
    val password: String,
    val isAdmin: Boolean = false
)