package me.qingshu.ktorexample.config.plugin

import io.ktor.server.application.*
import io.ktor.server.plugins.*

val RequestLoggingPlugin = createApplicationPlugin(
    "RequestLoggingPlugin",
    createConfiguration = ::RequestLoggingConfig
) {
    val log = application.log
    var enable = pluginConfig.enable
    onCall { call ->
        if(!enable) return@onCall
        call.request.origin.apply {
            log.info("Request URL: $scheme://$localHost:$localAddress:$localPort$uri")
        }
    }
}

class RequestLoggingConfig() {
    var enable = false
}