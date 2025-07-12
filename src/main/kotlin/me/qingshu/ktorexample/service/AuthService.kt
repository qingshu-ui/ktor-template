package me.qingshu.ktorexample.service

import me.qingshu.ktorexample.model.AuthResponse
import me.qingshu.ktorexample.model.User
import me.qingshu.ktorexample.model.UserLoginRequest
import me.qingshu.ktorexample.model.UserRegistrationRequest

/**
 * 认证服务接口
 * 定义用户认证、注册、JWT令牌生成和验证等功能
 */
interface AuthService {
    /**
     * 用户登录认证
     */
    suspend fun authenticateUser(request: UserLoginRequest): AuthResult
    
    /**
     * 用户注册
     */
    suspend fun registerUser(request: UserRegistrationRequest): AuthResult
    
    /**
     * 生成JWT令牌
     */
    suspend fun generateToken(user: User): String
    
    /**
     * 验证JWT令牌
     */
    suspend fun validateToken(token: String): User?
    
    /**
     * 刷新令牌
     */
    suspend fun refreshToken(token: String): AuthResult
}

/**
 * 认证结果密封类
 */
sealed class AuthResult {
    data class Success(val authResponse: AuthResponse) : AuthResult()
    data class Error(val message: String) : AuthResult()
} 