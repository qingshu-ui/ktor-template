package me.qingshu.ktorexample.config

import me.qingshu.ktorexample.repository.SessionRepository
import me.qingshu.ktorexample.repository.UserRepository
import me.qingshu.ktorexample.service.AuthService
import me.qingshu.ktorexample.service.PasswordEncoderService
import me.qingshu.ktorexample.service.UserService
import me.qingshu.ktorexample.service.impl.AuthServiceImpl
import me.qingshu.ktorexample.service.impl.PasswordEncoderServiceImpl
import me.qingshu.ktorexample.service.impl.UserServiceImpl
import org.koin.dsl.module

val appModule = module {

    // 数据访问层
    single { UserRepository() }
    single { SessionRepository() }

    // 服务层
    single<UserService> { UserServiceImpl(get()) }
    single<AuthService> { AuthServiceImpl(get(), get(), get()) }
    single<PasswordEncoderService> { PasswordEncoderServiceImpl() }
}