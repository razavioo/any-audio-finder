package dev.emad.framework.data.remote.request.model

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/audio")
class AudioRequest {
    @Serializable
    @Resource("{redirectionId}")
    class RedirectionId(val parent: AudioRequest = AudioRequest(), val redirectionId: String)
}