package com.emotionfactry.dreambook.authservice.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class DtoRefreshToken(
    val refreshToken: String
)
