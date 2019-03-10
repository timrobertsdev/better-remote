package us.timandkarli.betterrokuremote.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import us.timandkarli.betterrokuremote.models.RokuDevice
import us.timandkarli.betterrokuremote.network.HostSelectionInterceptor
import us.timandkarli.betterrokuremote.network.RokuService

class MainViewModel(
    private val hostSelectionInterceptor: HostSelectionInterceptor,
    private val rokuService: RokuService
) : ViewModel() {
    private val _viewState = MutableLiveData<MainViewState>()
    val viewState: LiveData<MainViewState> = _viewState

    init {
        _viewState.postValue(MainViewState.NoDeviceSelected)
    }

    fun setCurrentDevice(rokuDevice: RokuDevice) {
        hostSelectionInterceptor.host = rokuDevice.location
        _viewState.postValue(MainViewState.DeviceSelected(rokuDevice))
    }
}