package com.example.flutter_a2dp_android

import android.bluetooth.BluetoothAdapter
import android.content.Context
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel

class FlutterA2dpAndroidPlugin: FlutterPlugin {
  companion object {
    const val TAG: String = "FlutterA2dp"
    const val PREFS_KEY_HANDLE = "store_handle"
    const val PREFS_FILE = "flutter_a2dp_store"
  }

  private lateinit var channel : MethodChannel
  private lateinit var context: Context

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    context = flutterPluginBinding.applicationContext
    establishBluetoothMethodChannel(flutterPluginBinding.binaryMessenger)
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  private fun establishBluetoothMethodChannel(binaryMessenger: BinaryMessenger) {
    channel = MethodChannel(binaryMessenger, "a2dp")

    val methodHandler = BluetoothMethodHandler(
      bluetoothAdapter = BluetoothAdapter.getDefaultAdapter(),
      context = context
    )

    channel.setMethodCallHandler(methodHandler)
  }
}
