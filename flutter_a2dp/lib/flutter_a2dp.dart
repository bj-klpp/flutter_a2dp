import 'package:flutter_a2dp/src/platform_proxy.dart';

Future<List<Map>> getBondedSinks() async {
  return await platform.getBondedDevices();
}