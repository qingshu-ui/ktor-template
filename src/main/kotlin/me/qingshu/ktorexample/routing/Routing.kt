package me.qingshu.ktorexample.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.qingshu.ktorexample.config.GlobalJson
import me.qingshu.ktorexample.config.html.index
import me.qingshu.ktorexample.service.UserService
import org.koin.ktor.ext.inject

fun Application.configureRouting() = routing {
    get("/") {
        call.respondHtml {
            index()
        }
    }

    route("/users") {
        val userService: UserService by inject()
        get {
            val username = call.queryParameters["name"] ?: ""
            val id = call.queryParameters["id"]?.toLong() ?: -1
            if (username.isNotBlank()) {
                call.respondText {
                    GlobalJson {
                        encodeToString(userService.findUserByName(username))
                    }
                }
            } else if (id != -1L) {
                call.respondText {
                    GlobalJson {
                        encodeToString(userService.findUserById(id))
                    }
                }
            } else {
                call.respondText {
                    GlobalJson {
                        encodeToString(userService.getAllUser())
                    }
                }
            }
        }

        get("/create") {
            val name = call.request.queryParameters["name"] ?: return@get
            val newUser = userService.createUser(name)
            call.respondText { "User created: ${newUser.id} - ${newUser.name}" }
        }
    }

    staticResources("/static", "static") {
        cacheControl {
            return@cacheControl listOf(
                CacheControl.MaxAge(365 * 24 * 60 * 60)
            )
        }
    }
}