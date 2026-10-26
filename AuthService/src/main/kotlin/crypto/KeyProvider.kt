package com.emotionfactry.dreambook.authservice.crypto

import java.security.interfaces.ECPrivateKey
import java.security.interfaces.ECPublicKey

/**
 * provides public key which can be used to verify signature of data
 */
interface KeyProvider {
    fun getPublicKey(): ECPublicKey
}

/**
 * Provides the signing key as a Java key object, used by Algorithm.ECDSA256.
 * Для настоящего HSM (PKCS11-провайдер) это непрозрачный хендл — .getEncoded() у него
 * не вернёт сырые байты ключа, но Signature.initSign() с ним работает — ключ не покидает HSM.
 */
interface SigningKeyProvider : KeyProvider {
    fun getSigningKey(): ECPrivateKey

    companion object {
        fun create(region: DeploymentRegion = resolveRegion()): SigningKeyProvider =
            when (region) {
                DeploymentRegion.RUSSIA -> HsmSigningKeyProvider()
                DeploymentRegion.INTERNATIONAL -> CloudSecretKeyProvider()
                DeploymentRegion.LOCAL -> LocalSigningKeyProvider()
            }

        private fun resolveRegion(): DeploymentRegion =
            when (System.getenv("DEPLOYMENT_REGION")) {
                "RUSSIA" -> DeploymentRegion.RUSSIA
                "INTERNATIONAL" -> DeploymentRegion.INTERNATIONAL
                else -> DeploymentRegion.LOCAL // по умолчанию — безопасный стаб для локалки, не продакшен
            }
    }
}

enum class DeploymentRegion {
    RUSSIA,        // обязателен HSM (Приказ ФСБ №378, если подтвердится для JWT — см. Monetization.md)
    INTERNATIONAL, // облачный secrets-менеджер (Cloud.ru / AWS / GCP)
    LOCAL          // локальная разработка, ключ в памяти, не персистентен
}