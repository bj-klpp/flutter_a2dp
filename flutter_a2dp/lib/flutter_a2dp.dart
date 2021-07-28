import 'package:flutter_a2dp/src/platform_proxy.dart';

Future<int> getNumber() async {
  return await platform.getNumber();
}

Future<List<Map>> getBondedSinks() async {
  return await platform.getBondedSinks();
}