package com.example.flutter_a2dp_android

import android.bluetooth.BluetoothDevice

fun BluetoothDevice.toMap(): HashMap<String, Any>{
    return hashMapOf(
        "address" to address,
        "name" to name,
        "type" to type,
        "isConnected" to isConnected(),
        "bondState" to bondState
    )
}

fun BluetoothDevice.isConnected(): Boolean {
    return try {
        val method = javaClass.getMethod("isConnected")
        method.invoke(this) as Boolean
    } catch (e: Exception) {
        false
    }
}