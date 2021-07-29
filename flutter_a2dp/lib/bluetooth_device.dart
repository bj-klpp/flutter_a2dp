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

  @override
  String toString() {
    return 'BluetoothDevice{address: $address, name: $name, isConnected: $isConnected, uuids: $uuids}';
  }
}
