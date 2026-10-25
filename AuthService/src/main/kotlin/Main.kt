package com.emotionfactry.dreambook.authservice

import com.auth0.jwt.JWTVerifier
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

    lateinit var jwtVerifier: JWTVerifier

    embeddedServer(
        factory = Netty,
        port = 8080
    ) {
        module(jwtVerifier)
    }.start(wait = true)
}



fun Application.module(jwtVerifier: JWTVerifier) {
    configureSerialization()
    configureStatusPages()
    configureSecurity(jwtVerifier)
    configureRouting()
}

