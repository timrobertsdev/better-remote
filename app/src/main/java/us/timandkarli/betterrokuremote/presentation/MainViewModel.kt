package us.timandkarli.betterrokuremote.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import us.timandkarli.betterrokuremote.models.RokuDevice
import us.timandkarli.betterrokuremote.network.HostSelectionInterceptor

class MainViewModel(
    private val hostSelectionInterceptor: HostSelectionInterceptor
) : ViewModel() {
    private val _currentDevice = MutableLiveData<CurrentDeviceState>()
    val currentDevice: LiveData<CurrentDeviceState> = _currentDevice

    init {
        _currentDevice.postValue(CurrentDeviceState.NoDeviceSelected())
    }

    fun setCurrentDevice(rokuDevice: RokuDevice) {
        hostSelectionInterceptor.host = rokuDevice.location
        _currentDevice.postValue(CurrentDeviceState.DeviceSelected(rokuDevice))
    }
}