package me.qingshu.ktorexample.service.impl

import me.qingshu.ktorexample.model.User
import me.qingshu.ktorexample.repository.UserRepository
import me.qingshu.ktorexample.service.UserService
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class UserServiceImpl : UserService {

    override fun createUser(name: String): User {
        return transaction {
            val id = UserRepository.insertAndGetId {
                it[UserRepository.name] = name
            }.value
            User(id, name)
        }
    }

    override fun getAllUser(): List<User> {
        return transaction {
            UserRepository.selectAll().map { rowToUser(it) }
        }
    }

    override fun findUserById(id: Long): User? {
        return transaction {
            UserRepository.select(UserRepository.id eq id)
                .singleOrNull()
                ?.let {
                    rowToUser(it)
                }
        }
    }

    override fun findUserByName(name: String): User? {
        return transaction {
            UserRepository.select(UserRepository.name eq name)
                .singleOrNull()
                ?.let {
                    rowToUser(it)
                }
        }
    }

    override fun updateUser(id: Long, newName: String): Boolean {
        return transaction {
            UserRepository.update({ UserRepository.id eq id }) {
                it[name] = name
            } > 0
        }
    }

    private fun rowToUser(row: ResultRow): User {
        return User(
            id = row[UserRepository.id].value,
            name = row[UserRepository.name]
        )
    }
}