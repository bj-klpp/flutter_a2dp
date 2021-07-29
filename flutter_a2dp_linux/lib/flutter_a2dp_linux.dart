
import 'dart:async';

import 'package:flutter_a2dp_platform_interface/flutter_a2dp_platform_interface.dart';

class FlutterA2dpLinux extends FlutterA2dpInterface {
  static void registerWith() {
    FlutterA2dpInterface.instance = FlutterA2dpLinux();
  }
}
