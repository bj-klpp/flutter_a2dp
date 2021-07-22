package com.example.flutter_a2dp_android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.embedding.engine.loader.FlutterLoader
import io.flutter.view.FlutterCallbackInformation

class A2dpBroadcastReceiver : BroadcastReceiver() {
    private val TAG = "A2dpBroadcastReceiver"

    private var flutterEngine: FlutterEngine? = null
    private var flutterLoader: FlutterLoader? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "Broadcast received: ${intent?.action}")

        if (context != null) {
            if (flutterEngine == null) {
                flutterLoader = FlutterLoader().apply {
                    startInitialization(context)
                    ensureInitializationComplete(context, null)
                }

                flutterEngine = FlutterEngine(context)
            }

            val callbackHandle = context.getSharedPreferences(FlutterA2dpAndroidPlugin.PREFS_FILE, Context.MODE_PRIVATE)
                .getLong(FlutterA2dpAndroidPlugin.PREFS_KEY_HANDLE, 0)

            assert(callbackHandle != 0L)

            val callbackInfo =
                FlutterCallbackInformation.lookupCallbackInformation(callbackHandle)

            assert(callbackInfo != null)

            val args = flutterLoader?.let {
                DartExecutor.DartCallback(
                    context.assets,
                    it.findAppBundlePath(),
                    callbackInfo
                )
            }

            if (args != null) {
                flutterEngine!!.dartExecutor.executeDartCallback(args)
            }
        }
    }
}