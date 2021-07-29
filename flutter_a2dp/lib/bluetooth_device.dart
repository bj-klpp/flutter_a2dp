import 'package:flutter_a2dp/src/platform_proxy.dart';

class BluetoothDevice {
  String address;
  String name;
  bool isConnected;
  List<String> uuids;

  BluetoothDevice(
      {required this.address,
      required this.name,
      required this.isConnected,
      required this.uuids});

  factory BluetoothDevice.fromMap(Map<String, Object> map) => BluetoothDevice(
      address: map["address"] as String,
      name: map["name"] as String,
      isConnected: map["isConnected"] as bool,
      uuids: List<String>.from(map["uuids"] as List));

  bool get isAudioSink => _serviceUuids.contains("110B");

  Future<void> connectWithA2dp() async =>
      platform.connectToAddressWithA2dp(address);

  Iterable<String> get _serviceUuids =>
      uuids.map((e) => e.substring(4, 8).toUpperCase());

  @override
  String toString() {
    return 'BluetoothDevice{address: $address, name: $name, isConnected: $isConnected, uuids: $uuids}';
  }
}
