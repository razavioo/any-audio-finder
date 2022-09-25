package dev.emad.framework.data.local.dao

import dev.emad.business.model.User
import dev.emad.framework.data.local.model.LocalUser
import dev.emad.framework.data.local.model.LocalUsers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class UserDao(
    private val database: Database
) {
    fun isValidUser(username: String, password: String): Boolean = transaction(database) {
        LocalUsers.select {
            (LocalUsers.username eq username) and (LocalUsers.password eq password)
        }.count() > 0
    }

    fun isAdmin(username: String): Boolean = transaction(database) {
        val query = LocalUsers.select {
            (LocalUsers.username eq username) and (LocalUsers.isAdmin eq true)
        }.firstOrNull()
        query?.let { LocalUser.wrapRow(it) } != null
    }

    fun getFirst(id: Long): LocalUser? = transaction(database) {
        val query = LocalUsers.select { LocalUsers.id eq id }.firstOrNull()
        query?.let { LocalUser.wrapRow(it) }
    }

    fun getFirst(username: String): LocalUser? = transaction(database) {
        val query = LocalUsers.select { LocalUsers.username eq username }.firstOrNull()
        query?.let { LocalUser.wrapRow(it) }
    }

    fun getAll(): List<LocalUser> = transaction(database) {
        LocalUser.all().toList()
    }

    fun insert(user: User) {
        transaction(database) {
            LocalUsers.insert {
                it[firstName] = user.firstName
                it[lastName] = user.lastName
                it[username] = user.username
                it[password] = user.password
                it[isAdmin] = user.isAdmin
            }
        }
    }

    fun update(user: User) {
        transaction(database) {
            LocalUsers.update({ LocalUsers.id eq user.id }) {
                it[firstName] = user.firstName
                it[lastName] = user.lastName
                it[username] = user.username
                it[password] = user.password
                it[isAdmin] = user.isAdmin
            }
        }
    }

    fun delete(user: User) {
        transaction(database) {
            LocalUsers.deleteWhere {
                LocalUsers.id eq user.id
            }
        }
    }
}