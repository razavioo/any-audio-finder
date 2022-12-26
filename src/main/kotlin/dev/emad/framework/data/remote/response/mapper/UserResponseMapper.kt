package dev.emad.framework.data.remote.response.mapper

import dev.emad.framework.data.local.mapper.Mapper
import dev.emad.framework.data.remote.response.model.UserResponse
import dev.emad.business.model.User

class UserResponseMapper : Mapper<User, UserResponse>() {
    override fun from(value: User): UserResponse {
        return UserResponse(
            id = value.id,
            firstName = value.firstName,
            lastName = value.lastName,
            username = value.username,
            password = value.password,
            isAdmin = value.isAdmin
        )
    }
}