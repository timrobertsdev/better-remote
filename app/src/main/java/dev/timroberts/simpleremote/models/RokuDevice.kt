package dev.timroberts.simpleremote.models

data class RokuDevice(
    val location: String,
    val friendlyDeviceName: String,
    val deviceId: String
)