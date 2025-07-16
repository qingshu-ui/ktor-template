package me.qingshu.ktorexample.repository.impl

import me.qingshu.ktorexample.model.User
import me.qingshu.ktorexample.repository.UserRepository
import me.qingshu.ktorexample.utils.suspendTransaction
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

/**
 * 用户数据表定义
 */
object UserTable : LongIdTable() {
    val username = varchar("username", 255).uniqueIndex()
    val email = varchar("email", 255).uniqueIndex()
    val passwordHash = varchar("password_hash", 255)
    val createdAt = long("created_at")
    val updatedAt = long("updated_at")
}

class UserDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UserDao>(UserTable)

    var username by UserTable.username
    var email by UserTable.email
    var passwordHash by UserTable.passwordHash
    var createdAt by UserTable.createdAt
    var updatedAt by UserTable.updatedAt
}

/**
 * 用户数据访问对象
 */
class UserRepositoryImpl : UserRepository {

    /**
     * 根据用户名查找用户
     */
    override suspend fun findByUsername(username: String): User? = suspendTransaction {
        /*    UserTable.selectAll()
                .where { UserTable.username eq username }
                .map { rowToUser(it) }
                .singleOrNull()*/
        UserDao.find { UserTable.username eq username }
            .limit(1)
            .map(::rowToUser)
            .firstOrNull()
    }

    /**
     * 根据邮箱查找用户
     */
    override suspend fun findByEmail(email: String): User? = suspendTransaction {
        /*UserTable.selectAll()
            .where(UserTable.email eq email)
            .map { rowToUser(it) }
            .singleOrNull()*/
        UserDao.find { UserTable.email eq email }
            .limit(1)
            .map(::rowToUser)
            .firstOrNull()
    }

    /**
     * 根据ID查找用户
     */
    override suspend fun findById(id: Long): User? = suspendTransaction {
        /*       UserTable.selectAll()
                   .where(UserTable.id eq id)
                   .map { rowToUser(it) }
                   .singleOrNull()*/
        UserDao.findById(id)?.let(::rowToUser)
    }

    /**
     * 创建新用户
     */
    override suspend fun create(username: String, email: String, passwordHash: String): User = suspendTransaction {
        /*    val id = UserTable.insertAndGetId {
                it[UserTable.username] = username
                it[UserTable.email] = email
                it[UserTable.passwordHash] = passwordHash
                it[UserTable.createdAt] = System.currentTimeMillis()
                it[UserTable.updatedAt] = System.currentTimeMillis()
            }

            User(
                id = id.value,
                username = username,
                email = email,
                passwordHash = passwordHash,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )*/
        val user = UserDao.new {
            this.username = username
            this.email = email
            this.passwordHash = passwordHash
            createdAt = System.currentTimeMillis()
            updatedAt = System.currentTimeMillis()
        }.let(::rowToUser)
        user
    }

    /**
     * 更新用户信息
     */
    override suspend fun update(
        id: Long,
        username: String?,
        email: String?,
        passwordHash: String?,
    ): User? =
        suspendTransaction {
            /* val updateStatement = UserTable.update({ UserTable.id eq id }) { table ->
                 username?.let { table[UserTable.username] = it }
                 email?.let { table[UserTable.email] = it }
                 passwordHash?.let { table[UserTable.passwordHash] = it }
                 table[UserTable.updatedAt] = System.currentTimeMillis()
             }

             if (updateStatement > 0) {
                 findById(id)
             } else {
                 null
             }*/
            val updatedUser = UserDao.findById(id)?.apply {
                username?.run { this@apply.username = this }
                email?.run { this@apply.email = this }
                passwordHash?.run { this@apply.passwordHash = this }
            }?.let(::rowToUser)
            updatedUser
        }

    /**
     * 检查用户名是否存在
     */
    override suspend fun existsByUsername(username: String): Boolean = suspendTransaction {
        // UserTable.selectAll().where(UserTable.username eq username).count() > 0
        UserDao.find { UserTable.username eq username }.count() > 0
    }

    /**
     * 检查邮箱是否存在
     */
    override suspend fun existsByEmail(email: String): Boolean = suspendTransaction {
        // UserTable.selectAll().where(UserTable.email eq email).count() > 0
        UserDao.find { UserTable.email eq email }.count() > 0
    }

    /**
     * 将数据库行转换为User对象
     */
    private fun rowToUser(dao: UserDao): User = User(
        id = dao.id.value,
        username = dao.username,
        email = dao.email,
        passwordHash = dao.passwordHash,
        createdAt = dao.createdAt,
        updatedAt = dao.updatedAt,
    )
}