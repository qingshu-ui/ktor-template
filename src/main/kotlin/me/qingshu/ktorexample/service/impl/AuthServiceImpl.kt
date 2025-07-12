package me.qingshu.ktorexample.service.impl

import at.favre.lib.crypto.bcrypt.BCrypt
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import me.qingshu.ktorexample.config.JwtConfig
import me.qingshu.ktorexample.model.*
import me.qingshu.ktorexample.repository.UserRepositoryImpl
import me.qingshu.ktorexample.service.AuthResult
import me.qingshu.ktorexample.service.AuthService
import java.util.*

/**
 * 认证服务实现类
 */
class AuthServiceImpl(
    private val userRepository: UserRepositoryImpl,
    private val jwtConfig: JwtConfig
) : AuthService {
    
    /**
     * 用户登录认证
     */
    override suspend fun authenticateUser(request: UserLoginRequest): AuthResult {
        try {
            // 查找用户
            val user = userRepository.findByUsername(request.username)
                ?: return AuthResult.Error("用户名或密码错误")
            
            // 验证密码
            if (!verifyPassword(request.password, user.passwordHash ?: "")) {
                return AuthResult.Error("用户名或密码错误")
            }
            
            // 生成JWT令牌
            val token = generateToken(user)
            
            // 计算过期时间
            val expiresIn = if (request.rememberMe) {
                jwtConfig.expiresIn * 7 // 记住我时延长7倍时间
            } else {
                jwtConfig.expiresIn
            }
            
            return AuthResult.Success(
                AuthResponse(
                    user = user.toResponse(),
                    token = token,
                    expiresIn = expiresIn
                )
            )
        } catch (e: Exception) {
            return AuthResult.Error("认证过程中发生错误: ${e.message}")
        }
    }
    
    /**
     * 用户注册
     */
    override suspend fun registerUser(request: UserRegistrationRequest): AuthResult {
        try {
            // 验证输入
            val validationResult = validateRegistrationRequest(request)
            if (validationResult != null) {
                return AuthResult.Error(validationResult)
            }
            
            // 检查用户名是否已存在
            if (userRepository.existsByUsername(request.username)) {
                return AuthResult.Error("用户名已存在")
            }
            
            // 检查邮箱是否已存在
            if (userRepository.existsByEmail(request.email)) {
                return AuthResult.Error("邮箱已被注册")
            }
            
            // 加密密码
            val passwordHash = hashPassword(request.password)
            
            // 创建用户
            val user = userRepository.create(
                username = request.username,
                email = request.email,
                passwordHash = passwordHash
            )
            
            // 生成JWT令牌
            val token = generateToken(user)
            
            return AuthResult.Success(
                AuthResponse(
                    user = user.toResponse(),
                    token = token,
                    expiresIn = jwtConfig.expiresIn
                )
            )
        } catch (e: Exception) {
            return AuthResult.Error("注册过程中发生错误: ${e.message}")
        }
    }
    
    /**
     * 生成JWT令牌
     */
    override suspend fun generateToken(user: User): String {
        val algorithm = Algorithm.HMAC256(jwtConfig.secret)
        
        return JWT.create()
            .withIssuer(jwtConfig.issuer)
            .withAudience(jwtConfig.audience)
            .withClaim("userId", user.id)
            .withClaim("username", user.username)
            .withIssuedAt(Date())
            .withExpiresAt(Date(System.currentTimeMillis() + jwtConfig.expiresIn))
            .sign(algorithm)
    }
    
    /**
     * 验证JWT令牌
     */
    override suspend fun validateToken(token: String): User? {
        return try {
            val algorithm = Algorithm.HMAC256(jwtConfig.secret)
            val verifier = JWT.require(algorithm)
                .withIssuer(jwtConfig.issuer)
                .withAudience(jwtConfig.audience)
                .build()
            
            val decodedJWT = verifier.verify(token)
            val userId = decodedJWT.getClaim("userId").asLong()
            
            userRepository.findById(userId)
        } catch (e: JWTVerificationException) {
            null
        } catch (e: Exception) {
            null
        }
    }
    
    /**
     * 刷新令牌
     */
    override suspend fun refreshToken(token: String): AuthResult {
        val user = validateToken(token) ?: return AuthResult.Error("无效的令牌")
        
        val newToken = generateToken(user)
        
        return AuthResult.Success(
            AuthResponse(
                user = user.toResponse(),
                token = newToken,
                expiresIn = jwtConfig.expiresIn
            )
        )
    }
    
    /**
     * 加密密码
     */
    private fun hashPassword(password: String): String {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray())
    }
    
    /**
     * 验证密码
     */
    private fun verifyPassword(password: String, hashedPassword: String): Boolean {
        return BCrypt.verifyer().verify(password.toCharArray(), hashedPassword).verified
    }
    
    /**
     * 验证注册请求
     */
    private fun validateRegistrationRequest(request: UserRegistrationRequest): String? {
        if (request.username.isBlank() || request.username.length < 3) {
            return "用户名至少需要3个字符"
        }
        
        if (request.username.length > 20) {
            return "用户名不能超过20个字符"
        }
        
        if (!request.username.matches(Regex("^[a-zA-Z0-9_]+$"))) {
            return "用户名只能包含字母、数字和下划线"
        }
        
        if (request.email.isBlank() || !request.email.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)\$"))) {
            return "请输入有效的邮箱地址"
        }
        
        if (request.password.length < 6) {
            return "密码至少需要6个字符"
        }
        
        if (request.password.length > 128) {
            return "密码不能超过128个字符"
        }
        
        return null
    }
} 