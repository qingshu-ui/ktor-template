package me.qingshu.ktorexample.routing

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.qingshu.ktorexample.config.GlobalJson
import me.qingshu.ktorexample.config.html.index
import me.qingshu.ktorexample.model.UserSession
import me.qingshu.ktorexample.service.UserService
import org.koin.ktor.ext.inject

fun Application.configureRouting() = routing {
    // 认证路由
    authRouting()

    authenticate("auth_session") {
        get("/") {
            val userSession = call.principal<UserSession>()
            if (userSession != null) {
                call.respondHtml {
                    index(username = userSession.userName)
                }
            } else {
                call.respondRedirect("/login")
            }
        }
    }

    route("/users") {
        val userService: UserService by inject()
        get {
            call.respondText {
                GlobalJson {
                    encodeToString(userService.getAllUsers())
                }
            }
        }

        get("/create") {
            val username = call.request.queryParameters["username"] ?: return@get
            val email = call.request.queryParameters["email"] ?: return@get
            val password = call.request.queryParameters["password"] ?: return@get
            val newUser = userService.createUser(username, email, password)
            call.respondText { "User created: ${newUser.id} - ${newUser.username}" }
        }
    }

    staticResources("/static", "static") {
        // cacheControl {
        //     listOf(
        //         CacheControl.MaxAge(60)
        //     )
        // }
    }
}