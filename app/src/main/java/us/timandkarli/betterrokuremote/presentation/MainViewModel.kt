package us.timandkarli.betterrokuremote.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import us.timandkarli.betterrokuremote.models.RokuDevice

class MainViewModel : ViewModel() {
    private val _currentDevice = MutableLiveData<CurrentDeviceState>()
    val currentDevice: LiveData<CurrentDeviceState> = _currentDevice

    fun setCurrentDevice(rokuDevice: RokuDevice) {
        _currentDevice.postValue(CurrentDeviceState.DeviceSelected(rokuDevice))
    }
}