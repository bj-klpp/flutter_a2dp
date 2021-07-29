enum A2dpStatus {
  disconnected,
  connecting,
  connected,
  disconnecting
}

A2dpStatus a2dpStatusFromName(String name) {
  switch (name) {
    case "disconnected":
      return A2dpStatus.disconnected;
    case "connecting":
      return A2dpStatus.connecting;
    case "connected":
      return A2dpStatus.connected;
    case "disconnecting":
      return A2dpStatus.disconnecting;
    default:
      return A2dpStatus.disconnected;
  }
}
