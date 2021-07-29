import 'package:flutter/services.dart';
import 'package:flutter_a2dp_platform_interface/a2dp_status.dart';
import 'package:flutter_a2dp_platform_interface/flutter_a2dp_platform_interface.dart';

class A2dpMethodChannel extends FlutterA2dpInterface {
  static const MethodChannel _methodChannel =
      MethodChannel("com.github.polydome.fluttera2dp/bluetooth");
  static const EventChannel _eventChannel =
      EventChannel("com.github.polydome.fluttera2dp/status");

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

  @override
  Future<void> startListeningStatus() async {
    await _methodChannel.invokeMethod("startListeningStatus");
  }

  @override
  Future<void> stopListeningStatus() async {
    await _methodChannel.invokeMethod("stopListeningStatus");
  }

  @override
  Stream<A2dpStatus> get status => _eventChannel
      .receiveBroadcastStream()
      .map((statusName) => a2dpStatusFromName(statusName as String));
}