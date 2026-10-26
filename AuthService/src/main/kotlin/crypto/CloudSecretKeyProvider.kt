package com.emotionfactry.dreambook.authservice.crypto

import java.security.interfaces.ECPrivateKey
import java.security.interfaces.ECPublicKey

/** Для международного деплоя — Cloud.ru Secret Management / AWS Secrets Manager / GCP Secret Manager, под конкретного вендора. */
class CloudSecretKeyProvider(
//    private val secretsClient: SecretManagementClient
) : SigningKeyProvider {
    override fun getSigningKey(): ECPrivateKey {
        TODO("fetch из secrets-менеджера, распарсить в ECPrivateKey")
    }

    override fun getPublicKey(): ECPublicKey {
        TODO("fetch публичного ключа из secrets-менеджера")
    }
}