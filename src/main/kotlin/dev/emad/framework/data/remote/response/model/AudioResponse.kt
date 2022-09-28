package dev.emad.framework.data.remote.response.model

import kotlinx.serialization.Serializable

@Serializable
data class AudioResponse(
    val id: Long? = null,
    val pageUrl: String,
    val downloadUrl: String
)