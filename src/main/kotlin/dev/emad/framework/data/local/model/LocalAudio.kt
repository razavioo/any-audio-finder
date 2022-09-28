package dev.emad.framework.data.local.model

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

private const val AUDIO = "audio"

object LocalAudios : IdTable<Long>(AUDIO) {
    override val id: Column<EntityID<Long>> = long("id").autoIncrement().entityId()
    override val primaryKey: PrimaryKey = PrimaryKey(id)

    val pageUrl: Column<String> = text("page_url").uniqueIndex()
    val downloadUrl: Column<String> = text("download_url")
    val redirectionId: Column<String> = text("redirection_id").uniqueIndex()
    val createdAt: Column<LocalDateTime> = datetime("created_at").clientDefault { LocalDateTime.now() }
}

class LocalAudio(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<LocalAudio>(LocalAudios)

    var pageUrl by LocalAudios.pageUrl
    var downloadUrl by LocalAudios.downloadUrl
    var redirectionId by LocalAudios.redirectionId
    var createdAt by LocalAudios.createdAt
}