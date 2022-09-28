package dev.emad.framework.data.repository

import dev.emad.business.model.Audio
import dev.emad.framework.data.local.dao.AudioDao
import dev.emad.framework.data.local.mapper.LocalAudioMapper

class AudioRepositoryImpl(
    private val audioDao: AudioDao,
    private val audioMapper: LocalAudioMapper = LocalAudioMapper()
) : AudioRepository {
    override fun getFirstFromRedirectionId(redirectionId: String): Audio? {
        return audioDao.getFirstFromRedirectionId(redirectionId)?.let { audioMapper.from(it) }
    }

    override fun getFirstFromPageUrl(pageUrl: String): Audio? {
        return audioDao.getFirstFromPageUrl(pageUrl)?.let { audioMapper.from(it) }
    }

    override fun getAll(): List<Audio> {
        return audioDao.getAll().map {
            audioMapper.from(it)
        }
    }

    override fun insert(audio: Audio): Long {
        return audioDao.insert(audio)
    }

    override fun update(audio: Audio) {
        audioDao.update(audio)
    }

    override fun delete(audio: Audio) {
        audio.id?.let {
            audioDao.delete(it)
        }
    }
}