package me.qingshu.ktorexample.repository

import org.jetbrains.exposed.dao.id.LongIdTable

object UserRepository : LongIdTable() {
    val name = varchar("name", 255)
}