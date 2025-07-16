package me.qingshu.ktorexample.repository

import me.qingshu.ktorexample.model.UserSession

interface SessionRepository {
    suspend fun create(userId: Long, userAgent: String? = null, ipAddress: String? = null): Long
    suspend fun findByUserId(userId: Long): UserSession?
    suspend fun findBySessionId(sessionId: String): UserSession?
    suspend fun update(newSession: UserSession): Int
    suspend fun allSessions(): List<UserSession>
}