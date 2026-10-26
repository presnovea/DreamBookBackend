package com.emotionfactry.dreambook.authservice.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class RecordConsentRequest(
    val consentType: String,
    val policyVersion: String
)
