package com.example.flutter_a2dp_android

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

class FlutterA2dpAndroidPlugin: FlutterPlugin, MethodCallHandler {
  companion object {
    const val TAG: String = "FlutterA2dp"
    const val PREFS_KEY_HANDLE = "store_handle"
    const val PREFS_FILE = "flutter_a2dp_store"
  }

  private lateinit var channel : MethodChannel
  private lateinit var context: Context

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    context = flutterPluginBinding.applicationContext
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "a2dp")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    val args = call.arguments<ArrayList<*>>()
    Log.d(TAG, "method call ${call.method}")

    when (call.method) {
      "getNumber" -> result.success(4)
      "init" -> initialize(args, result)
      else -> result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  private fun initialize(args: ArrayList<*>, result: Result) {
    val rawHandle = args[0] as Long

    storeCallbackHandle(rawHandle)
    result.success(true)
  }

  private fun storeCallbackHandle(rawHandle: Long) {
    Log.d(TAG, "Storing raw handle [$rawHandle]")

    context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE).edit()
      .putLong(PREFS_KEY_HANDLE, rawHandle)
      .apply()
  }
}
