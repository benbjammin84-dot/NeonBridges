// src/main/java/com/neonbridge/VaultManager.kt

package com.neonbridge

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class VaultManager(context: Context) {

    private val sharedPreferences = EncryptedSharedPreferences.create(
        "encrypted_prefs",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveFile(key: String, value: ByteArray) {
        sharedPreferences.edit().putByteArray(key, value).apply()
    }

    fun loadFile(key: String): ByteArray? {
        return sharedPreferences.getByteArray(key, null)
    }
}
