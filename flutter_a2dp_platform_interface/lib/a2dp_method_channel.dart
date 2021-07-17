import 'package:flutter/services.dart';
import 'package:flutter_a2dp_platform_interface/flutter_a2dp_platform_interface.dart';

class A2dpMethodChannel extends FlutterA2dpInterface {
  static const MethodChannel _methodChannel = MethodChannel('a2dp');

  @override
  Future<int> getNumber() async {
    return await _methodChannel.invokeMethod<int>('getNumber') as int;
  }
}