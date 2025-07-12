package me.qingshu.ktorexample.config

import me.qingshu.ktorexample.repository.UserRepositoryImpl
import me.qingshu.ktorexample.service.AuthService
import me.qingshu.ktorexample.service.UserService
import me.qingshu.ktorexample.service.impl.AuthServiceImpl
import me.qingshu.ktorexample.service.impl.UserServiceImpl
import org.koin.dsl.module

val appModule = module {
    // JWT配置
    single { JwtConfig.default() }
    
    // 数据访问层
    single { UserRepositoryImpl() }
    
    // 服务层
    single<UserService> { UserServiceImpl(get()) }
    single<AuthService> { AuthServiceImpl(get(), get()) }
}