package com.emotionfactry.dreambook.authservice.services

import com.emotionfactry.dreambook.authservice.model.Email
import com.emotionfactry.dreambook.authservice.model.Password
import com.emotionfactry.dreambook.authservice.model.RefreshToken
import com.emotionfactry.dreambook.authservice.model.UserId
import com.emotionfactry.dreambook.authservice.model.dto.AuthResponse
import com.emotionfactry.dreambook.authservice.model.Directions
import com.emotionfactry.dreambook.authservice.model.dto.ProfileResponse

class AuthService {
    fun register(email: Email, password: Password) {}
    fun login(email: Email, password: Password): AuthResponse {
        return AuthResponse("", "")
    }
    fun refresh(token: RefreshToken): AuthResponse  {
        return AuthResponse("", "")
    }

    fun getProfile(userId: UserId): ProfileResponse {
        return ProfileResponse(userId.value.toString(), Directions.FOLK, true)
    }

    fun updateProfile(userId: UserId, direction: Directions): ProfileResponse {
        return ProfileResponse(userId.value.toString(), direction, true)
    }

    fun recordConsent(userId: UserId, consentType: String, policyVersion: String) {}
}