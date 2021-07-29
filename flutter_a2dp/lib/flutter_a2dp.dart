import 'dart:async';

import 'package:flutter_a2dp/bluetooth_device.dart';
import 'package:flutter_a2dp_platform_interface/a2dp_status.dart';
import 'package:flutter_a2dp/src/platform_proxy.dart';

export 'package:flutter_a2dp_platform_interface/a2dp_status.dart';

Future<List<BluetoothDevice>> getBondedSinks() async {
  final parsedDevices = platform
      .getBondedDevices()
      .then((rawDevices) => rawDevices.map((e) => BluetoothDevice.fromMap(e)))
      .then((deserializedDevices) =>
          deserializedDevices.where((device) => device.isAudioSink))
      .then(
          (deserializedSinks) => List<BluetoothDevice>.from(deserializedSinks));

  return parsedDevices;
}

class A2dp {
  Future<BluetoothDevice?> get connectedSink async {
    final rawSink = await platform.connectedSink;
    return rawSink != null ? BluetoothDevice.fromMap(rawSink) : null;
  }

  Stream<A2dpStatus> get status => platform.status;
}
