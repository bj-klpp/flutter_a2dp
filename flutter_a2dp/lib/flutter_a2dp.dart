import 'dart:async';

import 'package:flutter_a2dp/bluetooth_device.dart';
import 'package:flutter_a2dp_platform_interface/a2dp_status.dart';
import 'package:flutter_a2dp/src/platform_proxy.dart';

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
  late final _statusController = StreamController<A2dpStatus>(
    onListen: platform.startListeningStatus,
    onCancel: platform.stopListeningStatus,
    onPause: platform.stopListeningStatus,
    onResume: platform.startListeningStatus
  );


  A2dp() {
    _statusController.addStream(platform.status);
  }

  Stream<A2dpStatus> get status => _statusController.stream;
}
