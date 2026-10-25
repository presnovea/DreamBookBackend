package com.emotionfactry.dreambook.authservice.plugins

import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing { authRoutes() }
}