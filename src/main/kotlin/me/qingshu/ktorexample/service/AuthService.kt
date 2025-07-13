package me.qingshu.ktorexample.service

import me.qingshu.ktorexample.model.UserSession

interface AuthService {
    suspend fun authenticate(username: String, password: String): UserSession?
    suspend fun validateSession(session: UserSession?): Boolean
    suspend fun logout(session: UserSession)
}