package com.emotionfactry.dreambook.authservice.crypto

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.emotionfactry.dreambook.authservice.model.AccessToken
import com.emotionfactry.dreambook.authservice.model.RefreshToken
import com.emotionfactry.dreambook.authservice.model.UserId
import java.security.SecureRandom
import java.util.Base64
import java.util.Date

class TokenIssuer(
    keyProvider: SigningKeyProvider,
    private val issuer: String = "dreambook-auth-service",
    private val audience: String = "dreambook-backend"
) {
    // ECDSA256 принимает nullable private key — для чистой верификации (без подписи) можно передавать null,
    // но TokenIssuer всегда подписывает, поэтому keyProvider.getSigningKey() обязателен
    private val algorithm = Algorithm.ECDSA256(keyProvider.getPublicKey(), keyProvider.getSigningKey())

    fun createAccessToken(userId: UserId): AccessToken {
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