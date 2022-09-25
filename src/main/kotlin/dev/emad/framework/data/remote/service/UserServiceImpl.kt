package dev.emad.framework.data.remote.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import dev.emad.business.model.Jwt
import dev.emad.business.service.UserService
import dev.emad.framework.data.remote.request.mapper.UserRequestMapper
import dev.emad.framework.data.remote.request.model.UserRequest
import dev.emad.framework.data.remote.response.mapper.UserResponseMapper
import dev.emad.framework.data.remote.response.model.Response
import dev.emad.framework.data.repository.UserRepository
import io.ktor.http.*
import org.json.simple.JSONObject
import java.util.*

class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {
    override fun loginRespond(username: String, password: String): Response {
        val isValidUser = userRepository.isValidUser(username, password)
        val validUser = userRepository.getFirst(username)

        return if (isValidUser) {
            val jwt = Jwt.getInstance()
            val token = JWT.create()
                .withAudience(jwt.audience)
                .withIssuer(jwt.issuer)
                .withClaim("username", validUser?.username)
                .withClaim("isAdmin", validUser?.isAdmin)
                .withExpiresAt(Date(System.currentTimeMillis() + 60_000 * 60 * 24))
                .sign(Algorithm.HMAC256(jwt.secret))
            Response(
                JSONObject(mapOf("token" to token)),
                HttpStatusCode.OK
            )
        } else {
            Response(
                JSONObject(mapOf("message" to "Username and password combination is not valid!")),
                HttpStatusCode.Forbidden
            )
        }
    }

    override fun getAllUsersRespond(): Response {
        val users = userRepository.getAll()
        return if (users.isEmpty()) {
            Response(status = HttpStatusCode.NoContent, data = "")
        } else {
            val data = users.map { UserResponseMapper().from(it) }
            Response(status = HttpStatusCode.OK, data = data)
        }
    }

    override fun createUserRespond(userRequest: UserRequest): Response {
        val user = UserRequestMapper().from(userRequest)
        val existingUser = userRepository.getFirst(user.username)
        return if (existingUser == null) {
            userRepository.insertUser(user)
            userRepository.getFirst(user.username)?.let { insertedUser ->
                val userResponse = UserResponseMapper().from(insertedUser)
                Response(status = HttpStatusCode.OK, data = userResponse)
            } ?: run {
                Response(
                    JSONObject(mapOf("message" to "Unknown issue happened!!")),
                    HttpStatusCode.InternalServerError
                )
            }
        } else {
            Response(
                JSONObject(mapOf("message" to "Username is used before!")),
                HttpStatusCode.Conflict
            )
        }
    }
}