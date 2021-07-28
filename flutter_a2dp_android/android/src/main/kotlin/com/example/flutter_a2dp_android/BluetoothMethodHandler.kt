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
            "init" -> initialize(args, result)
            "getBondedSinks" -> getBondedSinks(args, result)
            else -> result.notImplemented()
        }
    }

    private fun initialize(args: ArrayList<*>, result: MethodChannel.Result) {
        val rawHandle = args[0] as Long

        storeCallbackHandle(rawHandle)
        result.success(true)
    }

    private fun getBondedSinks(args: ArrayList<*>, result: MethodChannel.Result) {
        val devices = bluetoothAdapter.bondedDevices
            .filter { it.isAudioSink }
            .map { it.toMap() }

        result.success(devices)
    }

    private fun storeCallbackHandle(rawHandle: Long) {
        Log.d(FlutterA2dpAndroidPlugin.TAG, "Storing raw handle [$rawHandle]")

        context.getSharedPreferences(FlutterA2dpAndroidPlugin.PREFS_FILE, Context.MODE_PRIVATE).edit()
            .putLong(FlutterA2dpAndroidPlugin.PREFS_KEY_HANDLE, rawHandle)
            .apply()
    }
}