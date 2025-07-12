package me.qingshu.ktorexample.model

import kotlinx.serialization.Serializable

/**
 * 用户数据模型
 * 包含用户的基本信息和认证相关字段
 */
@Serializable
data class User(
    val id: Long,
    val username: String,
    val email: String,
    val passwordHash: String? = null, // 不序列化密码哈希
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    /**
     * 创建用户响应对象，不包含敏感信息
     */
    fun toResponse(): UserResponse = UserResponse(
        id = id,
        username = username,
        email = email,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

/**
 * 用户响应数据模型
 * 用于API响应，不包含敏感信息
 */
@Serializable
data class UserResponse(
    val id: Long,
    val username: String,
    val email: String,
    val createdAt: Long,
    val updatedAt: Long
)

/**
 * 用户注册请求数据模型
 */
@Serializable
data class UserRegistrationRequest(
    val username: String,
    val email: String,
    val password: String
)

/**
 * 用户登录请求数据模型
 */
@Serializable
data class UserLoginRequest(
    val username: String,
    val password: String,
    val rememberMe: Boolean = false
)

/**
 * 认证响应数据模型
 */
@Serializable
data class AuthResponse(
    val user: UserResponse,
    val token: String,
    val expiresIn: Long
)