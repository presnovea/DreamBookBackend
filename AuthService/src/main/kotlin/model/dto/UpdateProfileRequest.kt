package com.emotionfactry.dreambook.authservice.model.dto

import com.emotionfactry.dreambook.authservice.model.Directions
import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileRequest(
    val userId: String,
    val preferredDirection: Directions?,
) {
}