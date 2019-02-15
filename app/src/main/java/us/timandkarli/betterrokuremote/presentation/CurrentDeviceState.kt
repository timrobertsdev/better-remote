package us.timandkarli.betterrokuremote.presentation

import us.timandkarli.betterrokuremote.models.RokuDevice

sealed class CurrentDeviceState {
    class NoDeviceSelected : CurrentDeviceState()
    data class DeviceSelected(val rokuDevice: RokuDevice) : CurrentDeviceState()
}