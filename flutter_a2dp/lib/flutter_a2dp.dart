import 'package:flutter_a2dp/src/platform_proxy.dart';

Future<int> getNumber() async {
  return await platform.getNumber();
}

Future<void> init() async {
  await platform.init();
}
