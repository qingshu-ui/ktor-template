package me.qingshu.ktorexample.service

interface PasswordEncoderService {
    suspend fun hash(password: String): String
    suspend fun verify(password: String, storedHash: String): Boolean
}