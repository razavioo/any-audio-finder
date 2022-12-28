package dev.emad.framework.data.remote.response.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AudioResponse(
    val id: Long? = null,
    @SerializedName("page_url")
    val pageUrl: String,
    @SerializedName("download_url")
    val downloadUrl: String
)