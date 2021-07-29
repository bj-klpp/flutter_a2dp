package com.example.flutter_a2dp_android

import android.content.Context
import io.flutter.plugin.common.EventChannel

class A2dpStatusStreamHandler(private val context: Context) :
    EventChannel.StreamHandler {
    private var receiver: A2dpStatusReceiver? = null

    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
        require(events != null) { "Events sink required" }

        if (receiver != null)
            return

        receiver = A2dpStatusReceiver(events)

        context.registerReceiver(
            receiver, A2dpStatusReceiver.createIntentFilter()
        )
    }

    override fun onCancel(arguments: Any?) {
        if (receiver == null)
            return

        context.unregisterReceiver(receiver)
        receiver = null
    }
}