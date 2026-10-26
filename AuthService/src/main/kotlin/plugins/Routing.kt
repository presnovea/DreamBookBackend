package com.emotionfactry.dreambook.authservice.plugins

import com.emotionfactry.dreambook.authservice.model.UserId
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.routing.routing
import java.util.UUID

fun Application.configureRouting() {
    routing { authRoutes() }
}
fun ApplicationCall.userId(): UserId {
    val principal = authentication.principal<JWTPrincipal>()!!
    return UserId(UUID.fromString(principal.subject))
}