import 'package:flutter_a2dp/bluetooth_device.dart';
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