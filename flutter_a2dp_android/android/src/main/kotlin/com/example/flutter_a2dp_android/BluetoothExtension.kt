package com.example.flutter_a2dp_android

import android.bluetooth.BluetoothDevice
import kotlin.collections.HashMap

fun BluetoothDevice.toMap(): HashMap<String, Any>{
    return hashMapOf(
        "address" to address,
        "name" to name,
        "isConnected" to isConnected,
        "uuids" to uuids.map { it.uuid.toString() }
    )
}

val BluetoothDevice.isConnected: Boolean
    get() = try {
        val method = javaClass.getMethod("isConnected")
        method.invoke(this) as Boolean
    } catch (e: Exception) {
        false
    }

val BluetoothDevice.isAudioSink: Boolean
    get() = this.serviceUUIDs.any { it == UUID_AUDIO_SINK }

private val BluetoothDevice.serviceUUIDs: List<Long>
    get() = this.uuids
        .map { it.uuid.mostSignificantBits ushr 32 }

private const val UUID_AUDIO_SINK = 0x110bL