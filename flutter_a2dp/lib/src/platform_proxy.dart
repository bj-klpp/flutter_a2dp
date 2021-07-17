import 'dart:io';

import 'package:flutter_a2dp_linux/flutter_a2dp_linux.dart';
import 'package:flutter_a2dp_platform_interface/a2dp_method_channel.dart';
import 'package:flutter_a2dp_platform_interface/flutter_a2dp_platform_interface.dart';

bool _registered = false;

FlutterA2dpInterface get platform {
  if (!_registered) register();

  return FlutterA2dpInterface.instance;
}

void register() {
  if (_manualRegistrationNeeded) {
    if (Platform.isLinux) {
      FlutterA2dpLinux.registerWith();
    }
  }

  _registered = true;
}

bool get _manualRegistrationNeeded =>
    FlutterA2dpInterface.instance is A2dpMethodChannel;
