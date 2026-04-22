// src/main/java/com/neonbridge/BridgeManager.kt

package com.neonbridge

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.neonbridge.ui.BridgePrompt
import com.neonbridge.ui.MeterView

class BridgeManager(private val context: Activity) {

    private val requiredPermissions = listOf(
        "android.permission.NEARBY_WIFI_DEVICES",
        "android.permission.BLUETOOTH_CONNECT",
        "android.permission.BLUETOOTH_ADVERTISE",
        "android.permission.FOREGROUND_SERVICE",
        "android.permission.VIBRATE",
        "android.permission.INTERNET"
    )

    fun checkPermissions() {
        val missingPermissions = requiredPermissions.filter { permission ->
            ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED
        }

        if (missingPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                context,
                missingPermissions.toTypedArray(),
                REQUEST_CODE_PERMISSIONS
            )
        }
    }

    fun onPermissionResult(granted: Boolean) {
        if (granted) {
            // Proceed with Wi-Fi Direct
        } else {
            BridgePrompt.show(context, "Permissions denied", "You need to grant permissions to use the app.")
        }
    }

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 1001
    }
}
