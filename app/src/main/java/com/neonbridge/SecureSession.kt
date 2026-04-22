// src/main/java/com/neonbridge/SecureSession.kt

package com.neonbridge

import org.libsodium.jni.Sodium
import java.security.SecureRandom

class SecureSession {

    private val sodium = Sodium()
    private var keyPair: Pair<ByteArray, ByteArray>? = null

    init {
        sodium.init()
    }

    fun generateKeyPair(): Pair<ByteArray, ByteArray> {
        val publicKey = ByteArray(32)
        val secretKey = ByteArray(64)
        Sodium.crypto_kx_keypair(publicKey, secretKey)
        keyPair = Pair(publicKey, secretKey)
        return keyPair!!
    }

    fun zeroOnClose() {
        keyPair?.let { (publicKey, secretKey) ->
            Sodium.memzero(publicKey, publicKey.size)
            Sodium.memzero(secretKey, secretKey.size)
        }
    }
}
