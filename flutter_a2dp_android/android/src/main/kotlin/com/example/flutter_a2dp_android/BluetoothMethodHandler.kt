package com.example.flutter_a2dp_android

import android.bluetooth.BluetoothAdapter
import android.util.Log
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class BluetoothMethodHandler(
    private val bluetoothAdapter: BluetoothAdapter,
    private val a2dpAdapter: A2dpAdapter
) : MethodChannel.MethodCallHandler {
    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        Log.d(TAG, "Method called [${call.method}]")

        when (call.method) {
            "getBondedDevices" -> getBondedDevices(result)
            "connectToAddressWithA2dp" -> connectToAddressWithA2dp(call, result)
            else -> result.notImplemented()
        }
    }

    private fun getBondedDevices(result: MethodChannel.Result) {
        result.success(bluetoothAdapter.bondedDevices.map { it.toMap() })
    }

    private fun connectToAddressWithA2dp(call: MethodCall, result: MethodChannel.Result) {
        if (!call.hasArgument("address")) {
            throw IllegalStateException("Missing argument: address")
        }

        val address = call.argument<String>("address")

        val device = bluetoothAdapter.getRemoteDevice(address)
        a2dpAdapter.connect(device)

        result.success(null)
    }
}