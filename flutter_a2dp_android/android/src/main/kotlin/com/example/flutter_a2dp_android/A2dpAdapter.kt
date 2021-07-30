package com.example.flutter_a2dp_android

import android.bluetooth.BluetoothA2dp
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothProfile
import android.content.Context
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class A2dpAdapter(private val a2dp: BluetoothA2dp) {
    private val connectMethod =
        a2dp.javaClass.getMethod("connect", BluetoothDevice::class.java)

    private val disconnectMethod =
        a2dp.javaClass.getMethod("disconnect", BluetoothDevice::class.java)

    val connectedSink get() = a2dp.connectedDevices.firstOrNull()

    fun connect(device: BluetoothDevice) {
        connectMethod(a2dp, device)
    }

    fun disconnect(device: BluetoothDevice) {
        disconnectMethod(a2dp, device)
    }
}

suspend fun BluetoothAdapter.getA2dpAdapter(context: Context): A2dpAdapter? = suspendCoroutine {
    getProfileProxy(context, object : BluetoothProfile.ServiceListener {
        override fun onServiceConnected(profile: Int, proxy: BluetoothProfile?) {
            it.resume(A2dpAdapter(proxy as BluetoothA2dp))
        }

        override fun onServiceDisconnected(profile: Int) {
            it.resume(null)
        }
    }, BluetoothProfile.A2DP);
}