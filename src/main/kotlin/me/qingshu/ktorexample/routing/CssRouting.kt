package me.qingshu.ktorexample.routing

import io.ktor.server.application.Application
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import me.qingshu.ktorexample.config.css.indexStyle
import me.qingshu.ktorexample.config.css.loginStyles
import me.qingshu.ktorexample.utils.respondCss

fun Application.configureCss() {
    routing {
        get("/index_style.css") {
            call.respondCss {
                indexStyle()
            }
        }
        get("/login.css") {
            call.respondCss {
                loginStyles()
            }
        }
    }
}