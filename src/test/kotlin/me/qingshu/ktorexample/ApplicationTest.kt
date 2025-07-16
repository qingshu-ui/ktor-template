package me.qingshu.ktorexample

import kotlinx.coroutines.runBlocking
import me.qingshu.ktorexample.config.appModule
import me.qingshu.ktorexample.repository.SessionRepository
import me.qingshu.ktorexample.utils.inject
import org.jetbrains.exposed.sql.Database
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.logger.SLF4JLogger
import kotlin.getValue

class ApplicationTest {
    init {
        Database.connect("jdbc:sqlite:ktor-template.db", "org.sqlite.JDBC")
        startKoin {
            modules(appModule)
            logger(SLF4JLogger())
        }
    }

    val sessionRepository: SessionRepository by inject()

    @Test
    fun sessionTest() {
        val allSession = runBlocking {
            sessionRepository.allSessions()
        }
        println(allSession)
    }
}