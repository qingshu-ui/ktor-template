package me.qingshu.ktorexample.service

import me.qingshu.ktorexample.model.User

interface UserService {
    suspend fun createUser(username: String, email: String, password: String): User
    suspend fun findUserById(id: Long): User?
    suspend fun findUserByUsername(username: String): User?
    suspend fun findUserByEmail(email: String): User?
    suspend fun getAllUsers(): List<User>
    suspend fun updateUser(id: Long, username: String? = null, email: String? = null, password: String? = null): User?
    suspend fun existsByUsername(username: String): Boolean
    suspend fun existsByEmail(email: String): Boolean
}