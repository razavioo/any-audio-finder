package dev.emad.framework.data.remote.service

import dev.emad.business.model.User
import dev.emad.business.service.SeederService
import dev.emad.framework.data.repository.UserRepository

class SeederServiceImpl(private val userRepository: UserRepository) : SeederService {
    override fun seed() {
        val baseUser = userRepository.getFirst(BASE_USERNAME)
        if (baseUser == null) {
            userRepository.insertUser(
                User(
                    firstName = "emad",
                    lastName = "razavi",
                    username = BASE_USERNAME,
                    password = BASE_PASSWORD,
                    isAdmin = true
                )
            )
        }
    }

    companion object {
        private const val BASE_USERNAME = "razavioo"
        private const val BASE_PASSWORD = "1234"
    }
}