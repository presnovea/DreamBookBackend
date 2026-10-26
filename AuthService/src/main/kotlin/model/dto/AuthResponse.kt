package com.emotionfactry.dreambook.authservice.model.dto

import io.ktor.server.auth.OAuthAccessTokenResponse

data class AuthResponse (
    val accessToken: String,
    val refreshToken: String
)