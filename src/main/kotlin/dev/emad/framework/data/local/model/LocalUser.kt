package dev.emad.framework.data.local.model

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

private const val USER = "user"

object LocalUsers : IdTable<Long>(USER) {
    override val id: Column<EntityID<Long>> = long("id").autoIncrement().entityId()
    override val primaryKey: PrimaryKey = PrimaryKey(id)

    val firstName: Column<String?> = text("first_name").nullable()
    val lastName: Column<String?> = text("last_name").nullable()
    val username: Column<String> = text("username").uniqueIndex()
    val password: Column<String> = text("password")
    val isAdmin: Column<Boolean> = bool("is_admin")
}

class LocalUser(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<LocalUser>(LocalUsers)

    val firstName by LocalUsers.firstName
    val lastName by LocalUsers.lastName
    val username by LocalUsers.username
    val password by LocalUsers.password
    val isAdmin by LocalUsers.isAdmin
}