package dev.emad.framework.data.repository

import dev.emad.business.model.Audio

interface AudioRepository {
    fun getFirstFromRedirectionId(redirectionId: String): Audio?
    fun getFirstFromPageUrl(pageUrl: String): Audio?
    fun getAll(): List<Audio>
    fun insert(audio: Audio): Long
    fun update(audio: Audio)
    fun delete(audio: Audio)
}