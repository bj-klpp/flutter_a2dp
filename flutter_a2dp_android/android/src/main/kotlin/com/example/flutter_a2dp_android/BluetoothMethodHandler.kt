package com.example.flutter_a2dp_android

import android.bluetooth.BluetoothAdapter
import android.util.Log
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class BluetoothMethodHandler(
    private val bluetoothAdapter: BluetoothAdapter
) : MethodChannel.MethodCallHandler {
    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        Log.d(TAG, "Method called [${call.method}]")

        when (call.method) {
            "getBondedDevices" -> getBondedDevices(result)
            else -> result.notImplemented()
        }
    }

    private fun getBondedDevices(result: MethodChannel.Result) {
        result.success(bluetoothAdapter.bondedDevices.map { it.toMap() })
    }
}