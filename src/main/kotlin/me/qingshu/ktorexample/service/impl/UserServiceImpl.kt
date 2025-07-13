package me.qingshu.ktorexample.service.impl

import at.favre.lib.crypto.bcrypt.BCrypt
import me.qingshu.ktorexample.model.User
import me.qingshu.ktorexample.repository.UserRepository
import me.qingshu.ktorexample.service.UserService

class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {

    override suspend fun createUser(username: String, email: String, passwordHash: String): User {
        // val passwordHash = BCrypt.withDefaults().hashToString(12, passwordHash.toCharArray())
        return userRepository.create(username, email, passwordHash)
    }

    override suspend fun getAllUsers(): List<User> {
        // 这里需要实现获取所有用户的逻辑
        // 暂时返回空列表，因为UserRepositoryImpl中没有实现这个方法
        return emptyList()
    }

    override suspend fun findUserById(id: Long): User? {
        return userRepository.findById(id)
    }

    override suspend fun findUserByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }

    override suspend fun findUserByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    override suspend fun updateUser(id: Long, username: String?, email: String?, password: String?): User? {
        val passwordHash = password?.let { BCrypt.withDefaults().hashToString(12, it.toCharArray()) }
        return userRepository.update(id, username, email, passwordHash)
    }

    override suspend fun existsByUsername(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }

    override suspend fun existsByEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }
}