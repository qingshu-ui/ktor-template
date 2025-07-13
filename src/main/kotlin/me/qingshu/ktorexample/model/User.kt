package me.qingshu.ktorexample.model

import kotlinx.datetime.Instant
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
)

@Serializable
data class UserSession(
    val sessionId: String,
    val userId: Long,
    val userName: String,
    var expiresAt: Instant,
)