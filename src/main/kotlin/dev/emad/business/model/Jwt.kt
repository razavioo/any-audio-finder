package dev.emad.business.model

data class Jwt(
    val issuer: String,
    val audience: String,
    val secret: String,
    val realm: String
) {
    companion object {
        fun getInstance() = Jwt(
            issuer = "http://0.0.0.0:8080/",
            audience = "http://0.0.0.0:8080/hello",
            secret = "secret-secret",
            realm = "Access to hello"
        )
    }
}