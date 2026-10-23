package com.emotionfactry.dreambook.authservice.services

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.emotionfactry.dreambook.authservice.model.AccessToken
import com.emotionfactry.dreambook.authservice.model.RefreshToken
import com.emotionfactry.dreambook.authservice.model.UserId
import java.security.SecureRandom
import java.security.interfaces.ECPrivateKey
import java.security.interfaces.ECPublicKey
import java.util.Base64
import java.util.Date

class TokenIssuer(
    privKey: ECPrivateKey,
    pubKey: ECPublicKey,
    private val issuer: String = "dreambook-auth-service",
    private val audience: String = "dreambook-backend"
) {
    private val algorithm = Algorithm.ECDSA256(pubKey, privKey)

    fun crateAccessToken(userId: UserId): AccessToken {
        return AccessToken(
            value =
                JWT.create()
                    .withSubject(userId.value.toString())
                    .withIssuer(issuer)
                    .withAudience(audience)
                    .withExpiresAt(Date(System.currentTimeMillis() + 15 * 60 * 1000))
                    .sign(algorithm)
        )
    }

    fun createRefreshToken(): RefreshToken {
        return RefreshToken(
            value =
            SecureRandom().let { rnd ->
                ByteArray(32).also { rnd.nextBytes(it) }
            }.let { Base64.getUrlEncoder().withoutPadding().encodeToString(it) }
        )
    }
}