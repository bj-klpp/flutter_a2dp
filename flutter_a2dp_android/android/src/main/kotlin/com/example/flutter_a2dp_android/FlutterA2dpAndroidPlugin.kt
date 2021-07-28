package com.example.flutter_a2dp_android

import android.bluetooth.BluetoothAdapter
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin

class FlutterA2dpAndroidPlugin: FlutterPlugin {
  private lateinit var bluetoothChannel: BluetoothMethodChannel

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    bluetoothChannel = BluetoothMethodChannel.create(
      binaryMessenger = flutterPluginBinding.binaryMessenger,
      handler = BluetoothMethodHandler(
        BluetoothAdapter.getDefaultAdapter()
      )
    )
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    bluetoothChannel.dispose()
  }
}
