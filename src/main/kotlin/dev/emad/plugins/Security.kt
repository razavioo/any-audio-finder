package dev.emad.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import dev.emad.business.model.Jwt
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

fun Application.configureSecurity() {
    authentication {
        val jwt = Jwt.getInstance()

        jwt("auth-customer") {
            val jwtAudience = jwt.audience
            realm = jwt.realm

            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwt.secret))
                    .withAudience(jwtAudience)
                    .withIssuer(jwt.issuer)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
            }
        }

        jwt("auth-admin") {
            val jwtAudience = jwt.audience
            realm = jwt.realm

            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwt.secret))
                    .withAudience(jwtAudience)
                    .withIssuer(jwt.issuer)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) {
                    val isAdmin = credential.payload.getClaim("isAdmin").asBoolean()
                    if (isAdmin) {
                        JWTPrincipal(credential.payload)
                    } else {
                        null
                    }
                } else {
                    null
                }
            }
            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
            }
        }
    }
}
