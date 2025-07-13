package me.qingshu.ktorexample.config.plugin

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import me.qingshu.ktorexample.model.UserSession
import me.qingshu.ktorexample.service.AuthService
import org.koin.ktor.ext.inject
import kotlin.time.Duration.Companion.days

fun Application.configureSessions() {
    install(Sessions) {
        cookie<UserSession>("auth_session") {
            cookie.maxAge = 7.days
        }
    }
}

fun Application.configureAuthentication() {
    val authService: AuthService by inject()
    install(Authentication) {
        session<UserSession>("auth_session") {
            validate { session ->
                if (authService.validateSession(session)) {
                    session
                } else null
            }
            challenge {
                call.respondRedirect("/login")
            }
        }
    }
}