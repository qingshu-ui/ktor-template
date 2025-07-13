package me.qingshu.ktorexample.repository

import kotlinx.datetime.Clock
import me.qingshu.ktorexample.model.UserSession
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.time.Duration.Companion.days
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

object SessionTable : LongIdTable() {
    val userId = reference("user_id", UserTable)
    val sessionId = varchar("session_id", 36).uniqueIndex()
    val createdAt = timestamp("created_at").default(Clock.System.now())
    val expiresAt = timestamp("expires_at")
    val lastAccessedAt = timestamp("last_access_at").default(Clock.System.now())
    val userAgent = varchar("user_agent", 255).nullable()
    val ipAddress = varchar("ip_address", 255).nullable()
}


class SessionRepository {

    @OptIn(ExperimentalUuidApi::class)
    fun create(
        userId: Long,
        userAgent: String? = null,
        ipAddress: String? = null,
    ) = transaction {
        SessionTable.insertAndGetId {
            it[SessionTable.userId] = userId
            it[SessionTable.sessionId] = Uuid.random().toString()
            it[SessionTable.createdAt] = Clock.System.now()
            it[SessionTable.expiresAt] = Clock.System.now() + 7.days
            it[SessionTable.lastAccessedAt] = Clock.System.now()
            it[SessionTable.userAgent] = userAgent
            it[SessionTable.ipAddress] = ipAddress
        }
    }

    fun findByUserId(userId: Long) = transaction {
        SessionTable.join(UserTable, JoinType.INNER, SessionTable.userId, UserTable.id)
            .selectAll()
            .where { SessionTable.userId eq userId }
            .singleOrNull()
            ?.let { rowToUserSession(it) }
    }

    fun findBySessionId(sessionId: String) = transaction {
        SessionTable.join(UserTable, JoinType.INNER, SessionTable.userId, UserTable.id)
            .selectAll().where { SessionTable.sessionId eq sessionId }
            .singleOrNull()
            ?.let { rowToUserSession(it) }
    }

    private fun rowToUserSession(row: ResultRow): UserSession {
        return UserSession(
            sessionId = row[SessionTable.sessionId],
            userId = row[SessionTable.userId].value,
            userName = row[UserTable.username],
            expiresAt = row[SessionTable.expiresAt]
        )
    }

    fun update(newSession: UserSession) = transaction {
        SessionTable.update({ SessionTable.sessionId eq newSession.sessionId }) {
            it[sessionId] = newSession.sessionId
            it[userId] = newSession.userId
            it[expiresAt] = newSession.expiresAt
        }
    }
}