package me.qingshu.ktorexample.config

import me.qingshu.ktorexample.service.UserService
import me.qingshu.ktorexample.service.impl.UserServiceImpl
import org.koin.dsl.module

val appModule = module {
    single<UserService> { UserServiceImpl() }
}