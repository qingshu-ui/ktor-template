package me.qingshu.ktorexample.service.impl

import at.favre.lib.crypto.bcrypt.BCrypt
import me.qingshu.ktorexample.service.PasswordEncoderService

class PasswordEncoderServiceImpl : PasswordEncoderService {
    override suspend fun hash(password: String): String {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray())
    }

    override suspend fun verify(password: String, storedHash: String): Boolean {
        return BCrypt.verifyer().verify(password.toCharArray(), storedHash).verified
    }
}