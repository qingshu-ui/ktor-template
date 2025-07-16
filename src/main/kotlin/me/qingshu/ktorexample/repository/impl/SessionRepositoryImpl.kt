package me.qingshu.ktorexample.repository.impl

import kotlinx.datetime.Clock
import me.qingshu.ktorexample.model.UserSession
import me.qingshu.ktorexample.repository.SessionRepository
import me.qingshu.ktorexample.utils.suspendTransaction
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp
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

class SessionDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<SessionDao>(SessionTable)

    var userId by UserDao referencedOn SessionTable.userId
    var sessionId by SessionTable.sessionId
    var createdAt by SessionTable.createdAt
    var expiresAt by SessionTable.expiresAt
    var lastAccessedAt by SessionTable.lastAccessedAt
    var userAgent by SessionTable.userAgent
    var ipAddress by SessionTable.ipAddress
}


class SessionRepositoryImpl : SessionRepository {

    @OptIn(ExperimentalUuidApi::class)
    override suspend fun create(
        userId: Long,
        userAgent: String?,
        ipAddress: String?,
    ) = suspendTransaction {
        /*    SessionTable.insertAndGetId {
                it[SessionTable.userId] = userId
                it[SessionTable.sessionId] = Uuid.random().toString()
                it[SessionTable.createdAt] = Clock.System.now()
                it[SessionTable.expiresAt] = Clock.System.now() + 7.days
                it[SessionTable.lastAccessedAt] = Clock.System.now()
                it[SessionTable.userAgent] = userAgent
                it[SessionTable.ipAddress] = ipAddress
            }*/
        val sessionDao = SessionDao.new {
            this.userId = UserDao.findById(userId) ?: error("userId not exists")
            sessionId = Uuid.random().toString()
            createdAt = Clock.System.now()
            expiresAt = Clock.System.now() + 7.days
            lastAccessedAt = Clock.System.now()
            this.userAgent = userAgent
            this.ipAddress = ipAddress
        }
        sessionDao.id.value
    }

    override suspend fun findByUserId(userId: Long) = suspendTransaction {
        /*SessionTable.join(UserTable, JoinType.INNER, SessionTable.userId, UserTable.id)
            .selectAll()
            .where { SessionTable.userId eq userId }
            .singleOrNull()
            ?.let { rowToUserSession(it) }*/
        val session = SessionDao.find { SessionTable.userId eq userId }
            .limit(1)
            .map(::rowToUserSession)
            .firstOrNull()
        session
    }

    override suspend fun findBySessionId(sessionId: String) = suspendTransaction {
        /*      SessionTable.join(UserTable, JoinType.INNER, SessionTable.userId, UserTable.id)
                  .selectAll().where { SessionTable.sessionId eq sessionId }
                  .singleOrNull()
                  ?.let { rowToUserSession(it) }*/
        val session = SessionDao.find { SessionTable.sessionId eq sessionId }
            .limit(1)
            .map(::rowToUserSession)
            .firstOrNull()
        session
    }

    override suspend fun update(newSession: UserSession) = suspendTransaction {
        /* SessionTable.update({ SessionTable.sessionId eq newSession.sessionId }) {
             it[sessionId] = newSession.sessionId
             it[userId] = newSession.userId
             it[expiresAt] = newSession.expiresAt
         }*/
        val session = SessionDao.find { SessionTable.sessionId eq newSession.sessionId }.toList()
        session.forEach {
            it.sessionId = newSession.sessionId
            it.userId = UserDao.findById(newSession.userId)!!
            it.expiresAt = newSession.expiresAt
        }
        session.size
    }

    override suspend fun allSessions() = suspendTransaction {
        SessionDao.all().map(::rowToUserSession)
    }

    private fun rowToUserSession(dao: SessionDao): UserSession {
        return UserSession(
            sessionId = dao.sessionId,
            userId = dao.userId.id.value,
            userName = dao.userId.username,
            expiresAt = dao.expiresAt
        )
    }


}