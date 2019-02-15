package us.timandkarli.betterrokuremote.presentation.discoverview

import android.net.wifi.WifiManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import timber.log.Timber
import us.timandkarli.betterrokuremote.models.RokuDevice
import us.timandkarli.betterrokuremote.network.HostSelectionInterceptor
import us.timandkarli.betterrokuremote.network.RokuService
import java.net.DatagramPacket
import java.net.InetAddress
import java.net.MulticastSocket
import java.net.SocketTimeoutException
import java.nio.charset.Charset

class DiscoverViewModel(
    private val api: RokuService,
    private val hostSelectionInterceptor: HostSelectionInterceptor,
    private val wifiManager: WifiManager
) : ViewModel() {
    private val _viewState = MutableLiveData<DiscoverViewState>()
    val viewState: LiveData<DiscoverViewState> = _viewState
    private var discoverJob: Job

    companion object {
        private const val ROKU_ECP_REQUEST = "M-SEARCH * HTTP/1.1\n" +
                "Host: 239.255.255.250:1900\n" +
                "Man: \"ssdp:discover\"\n" +
                "ST: roku:ecp\n"
        private const val ROKU_DISCOVER_IP = "239.255.255.250"
        private const val ROKU_DISCOVER_PORT = 1900
        // copied from android.util.patterns so the class doesn't depend on anything android.*
        private const val IP_ADDRESS_STRING = "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4]" +
                "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]" +
                "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}" +
                "|[1-9][0-9]|[0-9]))"
        private val IP_ADDRESS_REGEX = IP_ADDRESS_STRING.toRegex()
    }

    init {
        _viewState.value = DiscoverViewState()
        discoverJob = discover()
    }

    private fun discover(): Job {
        Timber.d("discover() called")
        Timber.w("If we don't get any results, make sure DiscoverFragment acquires the multicast lock before this method is called.")
        // We can't call cancelDiscover() first here because it will cause a NPE. Instead, we call it in the public
        // startDiscover() method. As this is an init method, the discoverJob shouldn't ever be running at this point anyway.
        _viewState.postValue(DiscoverViewState(state = DiscoverState.DISCOVERING, data = null))

        return CoroutineScope(Dispatchers.IO).launch {
            // Send discovery request
            val req = ROKU_ECP_REQUEST.toByteArray(Charset.defaultCharset())
            val socket = MulticastSocket(ROKU_DISCOVER_PORT)
            socket.soTimeout = 10
            val group = InetAddress.getByName(ROKU_DISCOVER_IP)
            socket.joinGroup(group)
            val packet = DatagramPacket(req, req.size, group, ROKU_DISCOVER_PORT)
            socket.send(packet)

            try {
                // 3 second timeout or until cancelled
                // TODO: Incrementally increase timeout if no devices are found
                withTimeout(5000) {
                    val multicastLock = wifiManager.createMulticastLock("multicastLock")
                    multicastLock?.apply {
                        setReferenceCounted(true)
                        acquire()
                    }

                    while (isActive) {
                        val buffer = ByteArray(300)

                        try {
                            socket.receive(DatagramPacket(buffer, buffer.size))
                        } catch (e: SocketTimeoutException) {
                            continue
                        }

                        val response = buffer.toString(Charset.defaultCharset())
                        if (response.startsWith("HTTP/1.1 200 OK")) {
                            val location = IP_ADDRESS_REGEX.find(response)?.value
                            if (location != null) {
                                Timber.d("Device found at: $location")

                                hostSelectionInterceptor.host = location

                                val deviceInfoResponse = api.getDeviceInfo()
                                val rokuDevice = RokuDevice(location, deviceInfoResponse.await().friendlyDeviceName)

                                // Reset host to null to avoid weird bugs
                                hostSelectionInterceptor.host = null
                                _viewState.postValue(_viewState.value!!.copy(data = rokuDevice))
                            }
                        }
                        // TODO: Better solution. yield() is required for withTimeout to work
                        yield()
                    }
                }
            } catch (e: TimeoutCancellationException) {
                Timber.d("discover() ended naturally")
                _viewState.postValue(DiscoverViewState(state = DiscoverState.FINISHED, data = null))
            } catch (e: CancellationException) {
                Timber.d("discover() cancelled")
                _viewState.postValue(DiscoverViewState(state = DiscoverState.CANCELLED, data = null))
            } finally {
                Timber.d("discover() cleaning up")
                socket.close()
            }
        }
    }

    fun startDiscover() {
        Timber.d("startDiscover() called")
        // Cancel previous discoverJob if running
        cancelDiscover()
        discoverJob = discover()
    }

     fun cancelDiscover() = runBlocking {
         Timber.d("cancelDiscover() called")
         if (discoverJob.isActive) {
             discoverJob.cancelAndJoin()
         }
    }

    fun setHost(host: String) {
        hostSelectionInterceptor.host = host
    }

    override fun onCleared() {
        super.onCleared()
        cancelDiscover()
    }
}