package me.qingshu.ktorexample.routing

import io.ktor.http.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.datetime.Clock
import me.qingshu.ktorexample.config.html.loginPage
import me.qingshu.ktorexample.config.html.registerPage
import me.qingshu.ktorexample.service.AuthService
import me.qingshu.ktorexample.service.PasswordEncoderService
import me.qingshu.ktorexample.service.UserService
import org.koin.ktor.ext.inject
import java.net.URLEncoder
import kotlin.time.DurationUnit

/**
 * 认证路由配置
 */
fun Route.authRouting() {

    val authService: AuthService by inject()
    val userService: UserService by inject()
    val passwordService: PasswordEncoderService by inject()

    route("/login") {

        post {
            val params = call.receiveParameters()
            val username = params["username"] ?: ""
            val password = params["password"] ?: ""
            val rememberMe = params["rememberMe"]?.toBoolean() ?: false
            val session = authService.authenticate(username, password)

            if (session != null) {
                val cookie = Cookie(
                    name = "session_id",
                    value = session.sessionId,
                    maxAge = (session.expiresAt - Clock.System.now()).toInt(DurationUnit.SECONDS),
                    httpOnly = true
                )
                call.sessions.set(session)
                call.response.cookies.append(
                    cookie
                )
                call.respondRedirect("/", permanent = false)
            } else {
                val redirectUrl = buildString {
                    append("/login?error=${URLEncoder.encode("账户不存在", "UTF-8")}")
                    if (username.isNotEmpty()) {
                        append("&username=${URLEncoder.encode(username, "UTF-8")}")
                    }
                    if (rememberMe) append("&rememberMe=true")
                }
                call.respondRedirect(redirectUrl, permanent = false)
            }
        }

        get {
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
    }

    route("/register") {

        get {
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

        post {
            try {
                val params = call.receiveParameters()
                val username = params["username"] ?: ""
                val email = params["email"] ?: ""
                val password = params["password"] ?: ""
                val confirmPassword = params["confirmPassword"] ?: ""

                if (password != confirmPassword) {
                    val redirectUrl = buildString {
                        append("/register?error=${URLEncoder.encode("两次输入的密码不一致", "UTF-8")}")
                        if (username.isNotEmpty()) {
                            append("&username=${URLEncoder.encode(username, "UTF-8")}")
                        }
                        if (email.isNotEmpty()) {
                            append("&email=${URLEncoder.encode(email, "UTF-8")}")
                        }
                    }
                    call.respondRedirect(redirectUrl, permanent = false)
                }

                val existUserName = userService.existsByUsername(username)
                val existEmail = userService.existsByEmail(email)
                if (!existUserName && !existEmail) {
                    userService.createUser(username, email, passwordService.hash(password))
                    call.respondRedirect("/login")
                } else {
                    // 重定向回注册页面并显示错误信息
                    val redirectUrl = buildString {
                        append("/register?error=${URLEncoder.encode("账户或邮箱已存在", "UTF-8")}")
                        if (username.isNotEmpty()) {
                            append("&username=${URLEncoder.encode(username, "UTF-8")}")
                        }
                        if (email.isNotEmpty()) {
                            append("&email=${URLEncoder.encode(email, "UTF-8")}")
                        }
                    }
                    call.respondRedirect(redirectUrl, permanent = false)
                }
            } catch (_: Throwable) {
                val redirectUrl = "/register?error=${URLEncoder.encode("注册过程中发生错误", "UTF-8")}"
                call.respondRedirect(redirectUrl, permanent = false)
            }
        }

        post("/logout") {
            call.response.cookies.append(
                Cookie(
                    name = "user_session",
                    value = "",
                    maxAge = 0,
                )
            )
            call.respondRedirect("/login?success=${URLEncoder.encode("已成功登出", "UTF-8")}", permanent = false)
        }
    }


    // // 获取当前用户信息（API端点）
    // get("/api/me") {
    //     val principal = call.principal<JWTPrincipal>()
    //
    //     if (principal != null) {
    //         val userId = principal.getClaim("userId", Long::class)
    //         val username = principal.getClaim("username", String::class)
    //
    //         if (userId != null && username != null) {
    //             val user = authService.validateToken(principal.payload.toString())
    //             if (user != null) {
    //                 call.respond(user.toResponse())
    //                 return@get
    //             }
    //         }
    //     }
    //
    //     call.respond(HttpStatusCode.Unauthorized, mapOf("error" to "未认证"))
    // }
    //
    // // 刷新令牌（API端点）
    // post("/api/auth/refresh") {
    //     try {
    //         val token = call.request.cookies["auth_token"]
    //
    //         if (token != null) {
    //             val result = authService.refreshToken(token)
    //
    //             when (result) {
    //                 is AuthResult.Success -> {
    //                     // 设置新的JWT令牌到Cookie
    //                     val cookie = Cookie(
    //                         name = "auth_token",
    //                         value = result.authResponse.token,
    //                         maxAge = (result.authResponse.expiresIn / 1000).toInt(),
    //                         httpOnly = true,
    //                         secure = false,
    //                     )
    //
    //                     call.response.cookies.append(cookie)
    //                     call.respond(result.authResponse)
    //                 }
    //
    //                 is AuthResult.Error -> {
    //                     call.respond(HttpStatusCode.Unauthorized, mapOf("error" to result.message))
    //                 }
    //             }
    //         } else {
    //             call.respond(HttpStatusCode.Unauthorized, mapOf("error" to "未提供令牌"))
    //         }
    //     } catch (e: Exception) {
    //         call.respond(HttpStatusCode.InternalServerError, mapOf("error" to "刷新令牌时发生错误"))
    //     }
    // }
} 