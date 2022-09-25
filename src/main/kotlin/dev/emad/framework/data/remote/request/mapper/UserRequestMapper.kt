package dev.emad.framework.data.remote.request.mapper

import com.horizon.common.Mapper
import dev.emad.business.model.User
import dev.emad.framework.data.remote.request.model.UserRequest

class UserRequestMapper : Mapper<UserRequest, User>() {
    override fun from(value: UserRequest): User {
        return User(
            firstName = value.firstName,
            lastName = value.lastName,
            username = value.username,
            password = value.password,
            isAdmin = value.isAdmin
        )
    }
}