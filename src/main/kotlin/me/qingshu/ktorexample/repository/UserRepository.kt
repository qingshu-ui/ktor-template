package me.qingshu.ktorexample.repository

import me.qingshu.ktorexample.model.User

interface UserRepository {
    suspend fun findByUsername(username: String): User?
    suspend fun findByEmail(email: String): User?
    suspend fun findById(id: Long): User?
    suspend fun create(username: String, email: String, passwordHash: String): User
    suspend fun update(id: Long, username: String? = null, email: String? = null, passwordHash: String? = null): User?
    suspend fun existsByUsername(username: String): Boolean
    suspend fun existsByEmail(email: String): Boolean
}