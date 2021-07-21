package com.example.flutter_a2dp_android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class A2dpBroadcastReceiver : BroadcastReceiver() {
    private val TAG = "A2dpBroadcastReceiver"

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "Broadcast received: ${intent?.action}")
    }
}