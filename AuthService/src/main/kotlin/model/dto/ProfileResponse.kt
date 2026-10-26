package com.emotionfactry.dreambook.authservice.model.dto

import com.emotionfactry.dreambook.authservice.model.Directions

data class ProfileResponse(
    val userId: String,
    val preferredDirection: Directions?,
    val onboardingCompleted: Boolean
)
