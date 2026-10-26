package com.emotionfactry.dreambook.authservice.crypto

import com.emotionfactry.dreambook.common.utils.generateKeyPair
import java.security.interfaces.ECPrivateKey
import java.security.interfaces.ECPublicKey

/** НЕ для продакшена — ключ живёт только в памяти процесса, пересоздаётся при каждом рестарте. Только для локальной разработки. */
class LocalSigningKeyProvider : SigningKeyProvider {
    private val keyPair = generateKeyPair()

    override fun getSigningKey(): ECPrivateKey = keyPair.private as ECPrivateKey

    override fun getPublicKey(): ECPublicKey = keyPair.public as ECPublicKey
}