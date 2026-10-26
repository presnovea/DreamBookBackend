package com.emotionfactry.dreambook.authservice

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.emotionfactry.dreambook.authservice.crypto.SigningKeyProvider
import com.emotionfactry.dreambook.authservice.crypto.TokenIssuer
import com.emotionfactry.dreambook.authservice.plugins.configureRouting
import com.emotionfactry.dreambook.authservice.plugins.configureSecurity
import com.emotionfactry.dreambook.authservice.plugins.configureSerialization
import com.emotionfactry.dreambook.authservice.plugins.configureStatusPages
import com.emotionfactry.dreambook.authservice.utils.createLogger
import com.emotionfactry.dreambook.authservice.utils.log
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    createLogger()
    log.info("Auth service is starting...")

    val keyProvider = SigningKeyProvider.create()
    val tokenIssuer = TokenIssuer(keyProvider)

    // verify-only Algorithm — приватный ключ не нужен для проверки, только публичный
    val jwtVerifier: JWTVerifier = JWT.require(Algorithm.ECDSA256(keyProvider.getPublicKey(), null))
        .withIssuer("dreambook-auth-service")
        .withAudience("dreambook-backend")
        .build()

    embeddedServer(
        factory = Netty,
        port = 8080
    ) {
        module(jwtVerifier, tokenIssuer)
    }.start(wait = true)
}

fun Application.module(jwtVerifier: JWTVerifier, tokenIssuer: TokenIssuer) {
    configureSerialization()
    configureStatusPages()
    configureSecurity(jwtVerifier)
    configureRouting()
}

