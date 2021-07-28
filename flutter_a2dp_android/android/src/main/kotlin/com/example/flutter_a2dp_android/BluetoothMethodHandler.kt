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
            "getNumber" -> result.success(4)
            "getBondedSinks" -> getBondedSinks(result)
            else -> result.notImplemented()
        }
    }

    private fun getBondedSinks(result: MethodChannel.Result) {
        val devices = bluetoothAdapter.bondedDevices
            .filter { it.isAudioSink }
            .map { it.toMap() }

        result.success(devices)
    }
}