package dev.emad.framework.data.local.mapper

import dev.emad.business.model.User
import dev.emad.framework.data.local.model.LocalUser

class LocalUserMapper : Mapper<LocalUser, User>() {
    override fun from(value: LocalUser): User {
        return User(
            id = value.id.value,
            firstName = value.firstName,
            lastName = value.lastName,
            username = value.username,
            password = value.password,
            isAdmin = value.isAdmin
        )
    }
}