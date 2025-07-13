package me.qingshu.ktorexample

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import me.qingshu.ktorexample.config.appModule
import me.qingshu.ktorexample.config.configureAuth
import me.qingshu.ktorexample.config.css.configureCss
import me.qingshu.ktorexample.repository.UserRepository
import me.qingshu.ktorexample.routing.configureRouting
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.context.startKoin
import org.koin.logger.SLF4JLogger

fun main() {
    val appProperties = serverConfig {
        module {
            module()
        }
        watchPaths = listOf(
            "classes",
            "res/main/resources"
        )
        developmentMode = true
    }
    embeddedServer(Netty, appProperties) {
        connector {
            port = 8080
            host = "0.0.0.0"
        }
    }.start(wait = true)
}

fun Application.module() {
    Database.connect(url = "jdbc:sqlite:ktor-template.db", driver = "org.sqlite.JDBC")
    transaction {
        SchemaUtils.create(UserRepository)
    }
    startKoin {
        modules(appModule)
        logger(SLF4JLogger())
    }
    configureAuth()
    configureRouting()
    configureCss()
}