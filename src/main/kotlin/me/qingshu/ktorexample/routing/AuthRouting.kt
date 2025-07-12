package me.qingshu.ktorexample.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.serialization.json.Json
import me.qingshu.ktorexample.config.html.loginPage
import me.qingshu.ktorexample.config.html.registerPage
import me.qingshu.ktorexample.model.UserLoginRequest
import me.qingshu.ktorexample.model.UserRegistrationRequest
import me.qingshu.ktorexample.service.AuthService
import me.qingshu.ktorexample.service.AuthResult
import org.koin.ktor.ext.inject

/**
 * 认证路由配置
 */
fun Route.authRouting() {
    val authService: AuthService by inject()
    
    // 登录页面
    get("/login") {
        val errorMessage = call.parameters["error"]
        val successMessage = call.parameters["success"]
        val username = call.parameters["username"] ?: ""
        val rememberMe = call.parameters["rememberMe"]?.toBoolean() ?: false
        
        call.respondHtml {
            loginPage(
                errorMessage = errorMessage,
                successMessage = successMessage,
                username = username,
                rememberMe = rememberMe
            )
        }
    }
    
    // 注册页面
    get("/register") {
        val errorMessage = call.parameters["error"]
        val successMessage = call.parameters["success"]
        val username = call.parameters["username"] ?: ""
        val email = call.parameters["email"] ?: ""
        
        call.respondHtml {
            registerPage(
                errorMessage = errorMessage,
                successMessage = successMessage,
                username = username,
                email = email
            )
        }
    }
    
    // 处理登录请求
    post("/login") {
        try {
            val formData = call.receiveParameters()
            val username = formData["username"] ?: ""
            val password = formData["password"] ?: ""
            val rememberMe = formData["rememberMe"]?.toBoolean() ?: false
            
            val loginRequest = UserLoginRequest(
                username = username,
                password = password,
                rememberMe = rememberMe
            )
            
            val result = authService.authenticateUser(loginRequest)
            
            when (result) {
                is AuthResult.Success -> {
                    // 设置JWT令牌到Cookie
                    val cookie = Cookie(
                        name = "auth_token",
                        value = result.authResponse.token,
                        maxAge = (result.authResponse.expiresIn / 1000).toInt(),
                        httpOnly = true,
                        secure = false, // 在生产环境中设置为true
                    )
                    
                    call.response.cookies.append(cookie)
                    
                    // 重定向到主页或指定页面
                    call.respondRedirect("/", permanent = false)
                }
                
                is AuthResult.Error -> {
                    // 重定向回登录页面并显示错误信息
                    val redirectUrl = buildString {
                        append("/login?error=${java.net.URLEncoder.encode(result.message, "UTF-8")}")
                        if (username.isNotEmpty()) {
                            append("&username=${java.net.URLEncoder.encode(username, "UTF-8")}")
                        }
                        if (rememberMe) {
                            append("&rememberMe=true")
                        }
                    }
                    call.respondRedirect(redirectUrl, permanent = false)
                }
            }
        } catch (e: Exception) {
            val redirectUrl = "/login?error=${java.net.URLEncoder.encode("登录过程中发生错误", "UTF-8")}"
            call.respondRedirect(redirectUrl, permanent = false)
        }
    }
    
    // 处理注册请求
    post("/register") {
        try {
            val formData = call.receiveParameters()
            val username = formData["username"] ?: ""
            val email = formData["email"] ?: ""
            val password = formData["password"] ?: ""
            val confirmPassword = formData["confirmPassword"] ?: ""
            
            // 验证密码确认
            if (password != confirmPassword) {
                val redirectUrl = buildString {
                    append("/register?error=${java.net.URLEncoder.encode("两次输入的密码不一致", "UTF-8")}")
                    if (username.isNotEmpty()) {
                        append("&username=${java.net.URLEncoder.encode(username, "UTF-8")}")
                    }
                    if (email.isNotEmpty()) {
                        append("&email=${java.net.URLEncoder.encode(email, "UTF-8")}")
                    }
                }
                call.respondRedirect(redirectUrl, permanent = false)
                return@post
            }
            
            val registrationRequest = UserRegistrationRequest(
                username = username,
                email = email,
                password = password
            )
            
            val result = authService.registerUser(registrationRequest)
            
            when (result) {
                is AuthResult.Success -> {
                    // 设置JWT令牌到Cookie
                    val cookie = Cookie(
                        name = "auth_token",
                        value = result.authResponse.token,
                        maxAge = (result.authResponse.expiresIn / 1000).toInt(),
                        httpOnly = true,
                        secure = false,
                    )
                    
                    call.response.cookies.append(cookie)
                    
                    // 重定向到主页
                    call.respondRedirect("/", permanent = false)
                }
                
                is AuthResult.Error -> {
                    // 重定向回注册页面并显示错误信息
                    val redirectUrl = buildString {
                        append("/register?error=${java.net.URLEncoder.encode(result.message, "UTF-8")}")
                        if (username.isNotEmpty()) {
                            append("&username=${java.net.URLEncoder.encode(username, "UTF-8")}")
                        }
                        if (email.isNotEmpty()) {
                            append("&email=${java.net.URLEncoder.encode(email, "UTF-8")}")
                        }
                    }
                    call.respondRedirect(redirectUrl, permanent = false)
                }
            }
        } catch (e: Exception) {
            val redirectUrl = "/register?error=${java.net.URLEncoder.encode("注册过程中发生错误", "UTF-8")}"
            call.respondRedirect(redirectUrl, permanent = false)
        }
    }
    
    // 登出
    post("/logout") {
        // 清除认证Cookie
        call.response.cookies.append(
            Cookie(
                name = "auth_token",
                value = "",
                maxAge = 0,
                httpOnly = true,
                secure = false,
            )
        )
        
        call.respondRedirect("/login?success=${java.net.URLEncoder.encode("已成功登出", "UTF-8")}", permanent = false)
    }
    
    // 获取当前用户信息（API端点）
    get("/api/me") {
        val principal = call.principal<JWTPrincipal>()
        
        if (principal != null) {
            val userId = principal.getClaim("userId", Long::class)
            val username = principal.getClaim("username", String::class)
            
            if (userId != null && username != null) {
                val user = authService.validateToken(principal.payload.toString())
                if (user != null) {
                    call.respond(user.toResponse())
                    return@get
                }
            }
        }
        
        call.respond(HttpStatusCode.Unauthorized, mapOf("error" to "未认证"))
    }
    
    // 刷新令牌（API端点）
    post("/api/auth/refresh") {
        try {
            val token = call.request.cookies["auth_token"]
            
            if (token != null) {
                val result = authService.refreshToken(token)
                
                when (result) {
                    is AuthResult.Success -> {
                        // 设置新的JWT令牌到Cookie
                        val cookie = Cookie(
                            name = "auth_token",
                            value = result.authResponse.token,
                            maxAge = (result.authResponse.expiresIn / 1000).toInt(),
                            httpOnly = true,
                            secure = false,
                        )
                        
                        call.response.cookies.append(cookie)
                        call.respond(result.authResponse)
                    }
                    
                    is AuthResult.Error -> {
                        call.respond(HttpStatusCode.Unauthorized, mapOf("error" to result.message))
                    }
                }
            } else {
                call.respond(HttpStatusCode.Unauthorized, mapOf("error" to "未提供令牌"))
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, mapOf("error" to "刷新令牌时发生错误"))
        }
    }
} 