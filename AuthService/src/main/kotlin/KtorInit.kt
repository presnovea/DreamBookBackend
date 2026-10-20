package com.emotionfactry.dreambook.authservice

import com.auth0.jwt.JWTVerifier
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun ktorInit(jwtVerifier: JWTVerifier) {
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) { json() }
        install(StatusPages) { /* единая обработка ошибок валидации/БД */ }
        install(Authentication) {
            jwt("auth-jwt") {
                verifier(jwtVerifier)
                validate { credential -> /* ... */ }
            }
        }
        routing {
            route("/auth") {
                post("/register") { /* bcrypt-хеш, запись в users */ }
                post("/login") { /* проверка пароля, выдача access+refresh токенов */ }
                post("/refresh") { /* обновление access-токена */ }
                authenticate("auth-jwt") {
                    get("/profile") { /* чтение user_profile */ }
                    put("/profile") { /* запись preferred_direction */ }
                    post("/consents") { /* запись в consents */ }
                }
            }
        }
    }.start(wait = true)
}