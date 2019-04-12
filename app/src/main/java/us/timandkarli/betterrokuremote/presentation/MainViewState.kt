package us.timandkarli.betterrokuremote.presentation

import us.timandkarli.betterrokuremote.models.RokuDevice

sealed class MainViewState {
    object Initializing : MainViewState()
    object ReconnectingLastDevice : MainViewState()
    object NoDeviceSelected : MainViewState()
    object DeviceDisconnected : MainViewState()
    data class DeviceSelected(val rokuDevice: RokuDevice) : MainViewState()
    object DeviceReconnected : MainViewState()
}