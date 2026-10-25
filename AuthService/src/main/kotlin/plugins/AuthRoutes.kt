package com.emotionfactry.dreambook.authservice.plugins

import io.ktor.server.auth.authenticate
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Route.authRoutes() {
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