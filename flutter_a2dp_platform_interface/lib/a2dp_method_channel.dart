import 'package:flutter/services.dart';
import 'package:flutter_a2dp_platform_interface/flutter_a2dp_platform_interface.dart';

class A2dpMethodChannel extends FlutterA2dpInterface {
  static const MethodChannel _methodChannel = MethodChannel("com.github.polydome.fluttera2dp/bluetooth");

  @override
  Future<List<Map<String, Object>>> getBondedDevices() async {
    final result = await _methodChannel
        .invokeMethod<List<Object?>>('getBondedDevices', []);

    final devices =
        result!.map((deviceMap) => Map<String, Object>.from(deviceMap as Map));

    return List<Map<String, Object>>.from(devices);
  }

  @override
  Future<void> connectToAddressWithA2dp(String address) async {
    return await _methodChannel.invokeMethod(
        "connectToAddressWithA2dp", <String, dynamic>{'address': address});
  }
}