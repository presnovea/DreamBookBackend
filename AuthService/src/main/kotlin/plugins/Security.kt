package com.emotionfactry.dreambook.authservice.plugins

import com.auth0.jwt.JWTVerifier
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.jwt

fun Application.configureSecurity(jwtVerifier: JWTVerifier) {
    install(Authentication) {
        jwt("auth-jwt") {
            verifier(jwtVerifier)
            validate { credential -> /* ... */ }
        }
    }
}