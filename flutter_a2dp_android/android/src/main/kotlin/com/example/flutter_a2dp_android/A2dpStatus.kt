package com.example.flutter_a2dp_android

import android.bluetooth.BluetoothA2dp
import android.bluetooth.BluetoothDevice
import android.content.Intent

sealed class A2dpStatus {
    abstract val name: String

    object Disconnected : A2dpStatus() {
        override val name: String = "disconnected"
    }

    object Connecting : A2dpStatus() {
        override val name: String = "connecting"
    }

    data class Connected(val device: BluetoothDevice) : A2dpStatus() {
        override val name: String = "connected"
    }

    object Disconnecting : A2dpStatus() {
        override val name: String = "disconnecting"
    }

    companion object {
        fun fromIntent(intent: Intent): A2dpStatus {
            require(intent.action == BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED) {
                "Not an A2DP intent: incompatible action"
            }

            val device = intent.extras?.getParcelable<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)

            return when (intent.extras?.getInt(BluetoothA2dp.EXTRA_STATE)) {
                BluetoothA2dp.STATE_DISCONNECTED -> Disconnected
                BluetoothA2dp.STATE_CONNECTING -> Connecting
                BluetoothA2dp.STATE_CONNECTED -> Connected(
                    device ?: throw IllegalArgumentException(
                        "FATAL: missing device extra"
                    )
                )
                BluetoothA2dp.STATE_DISCONNECTING -> Disconnecting
                else -> throw IllegalArgumentException("Not an A2DP intent: missing extras")
            }
        }
    }
}