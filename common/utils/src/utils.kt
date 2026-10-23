package com.emotionfactry.dreambook.utils

import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.spec.ECGenParameterSpec
/**
 * Creates keypair used for JWT signing and verification. Should be used once.
 * @return KeyPair containing private and public keys
 */
fun generateKeyPair(): KeyPair {
    val generator = KeyPairGenerator.getInstance("EC")
    generator.initialize(ECGenParameterSpec("secp256r1")) // Используем кривую p-256
    return generator.generateKeyPair()
}