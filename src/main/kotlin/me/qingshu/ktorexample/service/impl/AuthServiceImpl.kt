package me.qingshu.ktorexample.service.impl

import kotlinx.datetime.Clock
import me.qingshu.ktorexample.model.UserSession
import me.qingshu.ktorexample.repository.SessionRepository
import me.qingshu.ktorexample.repository.UserRepository
import me.qingshu.ktorexample.service.AuthService
import me.qingshu.ktorexample.service.PasswordEncoderService
import kotlin.time.Duration.Companion.seconds

class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository,
    private val passwordEncoderService: PasswordEncoderService,
) : AuthService {

    override suspend fun authenticate(
        username: String,
        password: String,
    ): UserSession? {
        val user = userRepository.findByUsername(username) ?: return null
        return if (passwordEncoderService.verify(password, user.passwordHash!!)) {
            val session = sessionRepository.findByUserId(user.id)
            if (session == null) {
                sessionRepository.create(user.id)
                authenticate(username, password)
            } else if (validateSession(session)) session
            else null
        } else null
    }

    override suspend fun validateSession(session: UserSession?): Boolean {
        if (session == null) return false
        val newSession = sessionRepository.findBySessionId(session.sessionId) ?: return false
        return newSession.expiresAt.minus(Clock.System.now()) > 0.seconds
    }

    override suspend fun logout(session: UserSession) {
        val newSession = sessionRepository.findBySessionId(session.sessionId) ?: return
        newSession.expiresAt = Clock.System.now()
        sessionRepository.update(newSession)
    }
}