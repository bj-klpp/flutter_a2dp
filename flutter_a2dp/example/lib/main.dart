import 'package:flutter/material.dart';
import 'package:flutter_a2dp/flutter_a2dp.dart';
import 'package:permission_handler/permission_handler.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  int number = 0;

  @override
  void initState() {
    Permission.location.request().then((value) {
      getBondedSinks().then((value) {
        print(value);
        final device = value.first;
        device.connectWithA2dp().then((value) => print("Probably connected"));
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      home: Scaffold(
        body: Text(number.toString()),
      ),
    );
  }
}