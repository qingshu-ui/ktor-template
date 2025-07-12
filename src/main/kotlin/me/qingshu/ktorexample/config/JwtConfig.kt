package me.qingshu.ktorexample.config

import kotlinx.serialization.Serializable

/**
 * JWT配置类
 * 包含JWT令牌生成和验证所需的所有配置参数
 */
@Serializable
data class JwtConfig(
    val secret: String,
    val issuer: String,
    val audience: String,
    val expiresIn: Long
) {
    companion object {
        /**
         * 默认JWT配置
         * 在生产环境中，secret应该通过环境变量设置
         */
        fun default(): JwtConfig = JwtConfig(
            secret = System.getenv("JWT_SECRET") ?: "your-secret-key-change-in-production",
            issuer = "ktor-template",
            audience = "ktor-template-users",
            expiresIn = 24 * 60 * 60 * 1000 // 24小时，以毫秒为单位
        )
    }
} 