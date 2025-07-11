package me.qingshu.ktorexample.config.css

import io.ktor.server.application.*
import io.ktor.server.routing.*
import kotlinx.css.*
import me.qingshu.ktorexample.utils.respondCss

fun Application.configureCss() = routing {
    get("/common.css") {
        call.respondCss {
            body {
                height = 100.vh
                backgroundColor = Color.seaGreen
                margin = Margin(0.px)
                display = Display.flex
                flexDirection = FlexDirection.column
                alignItems = Align.center
                justifyContent = JustifyContent.center
            }
            rule("h1.page-title") {
                color = Color.white
            }
        }
    }
}