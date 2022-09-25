package dev.emad.framework.data.repository

import dev.emad.business.model.User

interface UserRepository {
    fun isValidUser(username: String, password: String): Boolean
    fun isAdmin(username: String): Boolean
    fun getFirst(username: String): User?
    fun getAll(): List<User>
    fun insertUser(user: User)
}