package me.qingshu.ktorexample.service

import me.qingshu.ktorexample.model.User

interface UserService {
    fun createUser(name: String): User
    fun findUserById(id: Long): User?
    fun findUserByName(name: String): User?
    fun getAllUser(): List<User>
    fun updateUser(id: Long, newName: String): Boolean
}