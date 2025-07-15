package me.qingshu.ktorexample

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import me.qingshu.ktorexample.config.appModule
import me.qingshu.ktorexample.config.plugin.configureAuthentication
import me.qingshu.ktorexample.config.plugin.configureSessions
import me.qingshu.ktorexample.repository.SessionTable
import me.qingshu.ktorexample.repository.UserTable
import me.qingshu.ktorexample.routing.authRouting
import me.qingshu.ktorexample.routing.configureCss
import me.qingshu.ktorexample.routing.configureRouting
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.context.startKoin
import org.koin.logger.SLF4JLogger

fun main() {
    initialDb()

    embeddedServer(
        Netty,
        port = 8080,
        module = Application::module,
    ).start(wait = true)
}

fun initialDb() {
    Database.connect(url = "jdbc:sqlite:ktor-template.db", driver = "org.sqlite.JDBC")
    transaction {
        SchemaUtils.create(UserTable, SessionTable)
    }
    startKoin {
        modules(appModule)
        logger(SLF4JLogger())
    }
}

fun Application.module() {
    configureAuthentication()
    configureSessions()
    configureCss()
    authRouting()
    configureRouting()
}