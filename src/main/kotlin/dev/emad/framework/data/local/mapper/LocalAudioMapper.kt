package dev.emad.framework.data.local.mapper

import dev.emad.business.model.Audio
import dev.emad.framework.data.local.model.LocalAudio
import dev.emad.utils.DateUtils.toDate

class LocalAudioMapper : Mapper<LocalAudio, Audio>() {
    override fun from(value: LocalAudio): Audio {
        return Audio(
            id = value.id.value,
            pageUrl = value.pageUrl,
            downloadUrl = value.downloadUrl,
            redirectionId = value.redirectionId,
            createdAt = value.createdAt.toDate()
        )
    }
}