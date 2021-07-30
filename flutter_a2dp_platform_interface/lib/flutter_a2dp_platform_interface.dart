import 'package:flutter_a2dp_platform_interface/a2dp_method_channel.dart';
import 'package:flutter_a2dp_platform_interface/a2dp_status.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

abstract class FlutterA2dpInterface extends PlatformInterface {
  FlutterA2dpInterface() : super(token: _object);

  static final Object _object = Object();

  static FlutterA2dpInterface _a2dpInterface = A2dpMethodChannel();

  static FlutterA2dpInterface get instance => _a2dpInterface;

  static set instance(FlutterA2dpInterface instance) {
    PlatformInterface.verifyToken(instance, _object);
    _a2dpInterface = instance;
  }

  Future<Map<String, Object>?> get connectedSink =>
      throw UnimplementedError("getBondedDevices() has not been implemented");

  Future<List<Map<String, Object>>> getBondedDevices() async {
    throw UnimplementedError("getBondedDevices() has not been implemented");
  }

  Future<void> connectToAddressWithA2dp(String address) async {
    throw UnimplementedError(
        "connectToAddressWithA2dp() has not been implemented");
  }

  Stream<A2dpStatus> get status {
    throw UnimplementedError("status has not been implemented");
  }

  Future<void> disconnectFromAddressWithA2dp(String address) async {
    throw UnimplementedError(
        "disconnectToAddressWithA2dp() has not been implemented");
  }
}