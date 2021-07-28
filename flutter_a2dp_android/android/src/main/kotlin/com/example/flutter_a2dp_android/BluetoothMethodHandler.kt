package com.example.flutter_a2dp_android

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.util.Log
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class BluetoothMethodHandler(
    private val bluetoothAdapter: BluetoothAdapter,
    private val context: Context
) : MethodChannel.MethodCallHandler {
    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        val args = call.arguments<ArrayList<*>>()
        Log.d(FlutterA2dpAndroidPlugin.TAG, "method call ${call.method}")

        when (call.method) {
            "getNumber" -> result.success(4)
            "getBondedSinks" -> getBondedSinks(args, result)
            else -> result.notImplemented()
        }
    }

    private fun getBondedSinks(args: ArrayList<*>, result: MethodChannel.Result) {
        val devices = bluetoothAdapter.bondedDevices
            .filter { it.isAudioSink }
            .map { it.toMap() }

        result.success(devices)
    }
}