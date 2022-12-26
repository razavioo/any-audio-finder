package dev.emad.framework.data.remote.response.mapper

import dev.emad.framework.data.local.mapper.Mapper
import dev.emad.business.model.Audio
import dev.emad.framework.data.remote.response.model.AudioResponse

class AudioResponseMapper : Mapper<Audio, AudioResponse>() {
    override fun from(value: Audio): AudioResponse {
        val websiteUrl = System.getenv("API_URL")!!
        val downloadUrl = "$websiteUrl/audio/${value.redirectionId}"
        return AudioResponse(
            id = value.id,
            pageUrl = value.pageUrl,
            downloadUrl = downloadUrl
        )
    }
}