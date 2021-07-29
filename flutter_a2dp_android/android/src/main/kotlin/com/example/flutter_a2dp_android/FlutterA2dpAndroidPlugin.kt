package com.example.flutter_a2dp_android

import android.bluetooth.BluetoothAdapter
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.EventChannel
import kotlinx.coroutines.*

class FlutterA2dpAndroidPlugin: FlutterPlugin {
  private lateinit var bluetoothChannel: BluetoothMethodChannel
  private val coroutineScope = CoroutineScope(Dispatchers.Main)

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    coroutineScope.launch {
      val a2dpAdapter = withContext(Dispatchers.IO) {
        bluetoothAdapter.getA2dpAdapter(flutterPluginBinding.applicationContext)
      } ?: throw IllegalStateException("Unable to obtain A2DP proxy")

      bluetoothChannel = BluetoothMethodChannel.create(
        binaryMessenger = flutterPluginBinding.binaryMessenger,
        handler = BluetoothMethodHandler(
          bluetoothAdapter = bluetoothAdapter,
          a2dpAdapter = a2dpAdapter
        )
      )
    }

    val statusChannel = EventChannel(flutterPluginBinding.binaryMessenger, CHANNEL_STATUS)
    val statusStreamHandler = A2dpStatusStreamHandler(flutterPluginBinding.applicationContext)
    statusChannel.setStreamHandler(statusStreamHandler)
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    bluetoothChannel.dispose()
    coroutineScope.cancel()
  }
}
