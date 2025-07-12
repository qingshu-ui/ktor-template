package me.qingshu.ktorexample.repository

import me.qingshu.ktorexample.model.User
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

/**
 * 用户数据表定义
 */
object UserRepository : LongIdTable("users") {
    val username = varchar("username", 255).uniqueIndex()
    val email = varchar("email", 255).uniqueIndex()
    val passwordHash = varchar("password_hash", 255)
    val createdAt = long("created_at")
    val updatedAt = long("updated_at")
}

/**
 * 用户数据访问对象
 */
class UserRepositoryImpl {

    /**
     * 根据用户名查找用户
     */
    fun findByUsername(username: String): User? = transaction {
        UserRepository.select(UserRepository.username eq username)
            .map { rowToUser(it) }
            .singleOrNull()
    }

    /**
     * 根据邮箱查找用户
     */
    fun findByEmail(email: String): User? = transaction {
        UserRepository.select(UserRepository.email eq email)
            .map { rowToUser(it) }
            .singleOrNull()
    }

    /**
     * 根据ID查找用户
     */
    fun findById(id: Long): User? = transaction {
        UserRepository.select(UserRepository.id eq id)
            .map { rowToUser(it) }
            .singleOrNull()
    }

    /**
     * 创建新用户
     */
    fun create(username: String, email: String, passwordHash: String): User = transaction {
        val id = UserRepository.insertAndGetId {
            it[UserRepository.username] = username
            it[UserRepository.email] = email
            it[UserRepository.passwordHash] = passwordHash
            it[UserRepository.createdAt] = System.currentTimeMillis()
            it[UserRepository.updatedAt] = System.currentTimeMillis()
        }

        User(
            id = id.value,
            username = username,
            email = email,
            passwordHash = passwordHash,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )
    }

    /**
     * 更新用户信息
     */
    fun update(id: Long, username: String? = null, email: String? = null, passwordHash: String? = null): User? =
        transaction {
            val updateStatement = UserRepository.update({ UserRepository.id eq id }) { table ->
                username?.let { table[UserRepository.username] = it }
                email?.let { table[UserRepository.email] = it }
                passwordHash?.let { table[UserRepository.passwordHash] = it }
                table[UserRepository.updatedAt] = System.currentTimeMillis()
            }

            if (updateStatement > 0) {
                findById(id)
            } else {
                null
            }
        }

    /**
     * 检查用户名是否存在
     */
    fun existsByUsername(username: String): Boolean = transaction {
        UserRepository.select (UserRepository.username eq username).count() > 0
    }

    /**
     * 检查邮箱是否存在
     */
    fun existsByEmail(email: String): Boolean = transaction {
        UserRepository.select (UserRepository.email eq email).count() > 0
    }

    /**
     * 将数据库行转换为User对象
     */
    private fun rowToUser(row: ResultRow): User = User(
        id = row[UserRepository.id].value,
        username = row[UserRepository.username],
        email = row[UserRepository.email],
        passwordHash = row[UserRepository.passwordHash],
        createdAt = row[UserRepository.createdAt],
        updatedAt = row[UserRepository.updatedAt]
    )
}