package dev.emad.framework.data.local.dao

import dev.emad.business.model.Audio
import dev.emad.framework.data.local.model.LocalAudio
import dev.emad.framework.data.local.model.LocalAudios
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class AudioDao(
    private val database: Database
) {
    fun getFirstFromRedirectionId(redirectionId: String): LocalAudio? = transaction(database) {
        val query = LocalAudios.select {
            LocalAudios.redirectionId eq redirectionId
        }.firstOrNull()
        query?.let { LocalAudio.wrapRow(it) }
    }

    fun getFirstFromPageUrl(pageUrl: String): LocalAudio? = transaction(database) {
        val query = LocalAudios.select {
            LocalAudios.pageUrl eq pageUrl
        }.firstOrNull()
        query?.let { LocalAudio.wrapRow(it) }
    }

    fun getAll(): List<LocalAudio> = transaction(database) {
        LocalAudio.all().toList()
    }

    fun insert(audio: Audio): Long {
        return transaction(database) {
            val resultRow = LocalAudios.insert {
                it[pageUrl] = audio.pageUrl
                it[downloadUrl] = audio.downloadUrl
                it[redirectionId] = audio.redirectionId
            }.resultedValues!!.first()

            LocalAudio.wrapRow(resultRow).id.value
        }
    }

    fun update(audio: Audio) {
        transaction(database) {
            LocalAudios.update({ LocalAudios.id eq audio.id }) {
                it[pageUrl] = audio.pageUrl
                it[downloadUrl] = downloadUrl
                it[redirectionId] = redirectionId
            }
        }
    }

    fun delete(audioId: Long) {
        transaction(database) {
            LocalAudios.deleteWhere {
                LocalAudios.id eq audioId
            }
        }
    }
}