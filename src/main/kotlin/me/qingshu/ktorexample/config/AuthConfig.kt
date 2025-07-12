package me.qingshu.ktorexample.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import org.koin.ktor.ext.inject

/**
 * JWT认证配置
 */
fun Application.configureAuth() {
    val jwtConfig: JwtConfig by inject()
    
    install(Authentication) {
        jwt("auth-jwt") {
            realm = "ktor-template"
            verifier(
                JWT.require(Algorithm.HMAC256(jwtConfig.secret))
                    .withIssuer(jwtConfig.issuer)
                    .withAudience(jwtConfig.audience)
                    .build()
            )
            validate { credential ->
                if (credential.payload.issuer == jwtConfig.issuer) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
        }
    }
} 