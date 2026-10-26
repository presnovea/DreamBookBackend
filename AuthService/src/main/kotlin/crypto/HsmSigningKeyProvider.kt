package com.emotionfactry.dreambook.authservice.crypto

import java.security.interfaces.ECPrivateKey
import java.security.interfaces.ECPublicKey

/** Для РФ-деплоя — требование Приказа ФСБ №378, если подтвердится, что подпадает под СКЗИ-мандат (см. Monetization.md). */
class HsmSigningKeyProvider(/* crypto pro/rutoken PKCS11 provider, alias ключа */) : SigningKeyProvider {
    override fun getSigningKey(): ECPrivateKey {
        TODO("получить через PKCS11 KeyStore (CryptoPro/Рутокен) — непрозрачный хендл, не экспортируемый")
    }

    override fun getPublicKey(): ECPublicKey {
        TODO("читается с HSM, это не секрет")
    }
}
