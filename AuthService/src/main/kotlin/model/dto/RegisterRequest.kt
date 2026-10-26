package com.emotionfactry.dreambook.authservice.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val email: String,
    val password: String)