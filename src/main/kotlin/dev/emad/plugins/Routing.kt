package dev.emad.plugins

import dev.emad.business.service.SeederService
import dev.emad.di.ProjectModule
import dev.emad.framework.data.remote.request.model.AudioRequest
import dev.emad.framework.data.repository.AudioRepository
import dev.emad.framework.route.coreRoutes
import dev.emad.framework.route.userRoutes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.koin.logger.SLF4JLogger

fun Application.configureRouting() {
    install(Koin) {
        SLF4JLogger()
        modules(ProjectModule)
    }

    install(StatusPages) {
        exception<AuthenticationException> { call, cause ->
            call.respond(HttpStatusCode.Unauthorized)
        }
        exception<AuthorizationException> { call, cause ->
            call.respond(HttpStatusCode.Forbidden)
        }
        exception<NotFoundException> { call, cause ->
            call.respond(HttpStatusCode.NotFound)
        }
    }
    install(Resources)

    routing {
        val seederService: SeederService by inject()
        val audioRepository: AudioRepository by inject()

        seederService.seed()

        coreRoutes()
        userRoutes()

        get<AudioRequest.RedirectionId> { audio ->
            val existingAudio = audioRepository.getFirstFromRedirectionId(audio.redirectionId)
            if (existingAudio == null) {
                call.respond(HttpStatusCode.NotFound, "This audio url is not found.")
            } else {
                call.respondRedirect(existingAudio.downloadUrl, false)
            }
        }
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
