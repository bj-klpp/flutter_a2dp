package com.example.flutter_a2dp_android

import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel

class BluetoothMethodChannel private constructor(
    private val channel: MethodChannel,
    private val handler: BluetoothMethodHandler
) {
    companion object {
        fun create(
            binaryMessenger: BinaryMessenger,
            handler: BluetoothMethodHandler
        ): BluetoothMethodChannel {
            return BluetoothMethodChannel(
                channel = MethodChannel(binaryMessenger, CHANNEL_BLUETOOTH),
                handler = handler
            )
        }
    }

    init {
        channel.setMethodCallHandler(handler)
    }

    fun dispose() {
        channel.setMethodCallHandler(null)
    }
}