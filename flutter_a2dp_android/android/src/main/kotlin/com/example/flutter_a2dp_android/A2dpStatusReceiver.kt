package com.example.flutter_a2dp_android

import android.bluetooth.BluetoothA2dp
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import io.flutter.plugin.common.EventChannel

class A2dpStatusReceiver(private val statusSink: EventChannel.EventSink) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null && intent.action == BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED) {
            val status = A2dpStatus.fromIntent(intent)
            statusSink.success(status.name)
        }
    }

    companion object {
        fun createIntentFilter(): IntentFilter = IntentFilter(
            BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED
        )
    }
}