package com.emotionfactry.dreambook.authservice.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages

fun Application.configureStatusPages() {
    install(StatusPages) { /* единая обработка ошибок валидации/БД */ }
}