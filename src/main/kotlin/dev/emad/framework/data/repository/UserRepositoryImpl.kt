package dev.emad.framework.data.repository

import dev.emad.business.model.User
import dev.emad.framework.data.local.dao.UserDao
import dev.emad.framework.data.local.mapper.LocalUserMapper

class UserRepositoryImpl(
    private val userDao: UserDao,
    private val userMapper: LocalUserMapper = LocalUserMapper()
) : UserRepository {
    override fun isValidUser(username: String, password: String): Boolean {
        return userDao.isValidUser(username, password)
    }

    override fun isAdmin(username: String): Boolean {
        return userDao.isAdmin(username)
    }

    override fun getFirst(username: String): User? {
        return userDao.getFirst(username)?.let { userMapper.from(it) }
    }

    override fun getAll(): List<User> {
        return userDao.getAll().map {
            userMapper.from(it)
        }
    }

    override fun insertUser(user: User) {
        userDao.insert(user)
    }
}
