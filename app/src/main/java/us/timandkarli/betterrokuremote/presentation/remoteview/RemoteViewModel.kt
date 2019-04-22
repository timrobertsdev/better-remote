package us.timandkarli.betterrokuremote.presentation.remoteview

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import us.timandkarli.betterrokuremote.network.RokuService

@Suppress("DeferredResultUnused")
class RemoteViewModel(private val rokuService: RokuService) : ViewModel() {
    companion object {
        // TODO: Change to configurable setting
        private const val VOLUME_STEP = 5
    }

    fun volumeUp() = CoroutineScope(Dispatchers.IO).launch {
        // TODO: Error handling for all sendkey commands, possibly all calls
        repeat(VOLUME_STEP) {
            rokuService.sendKeyAsync("VolumeUp").await()
        }
    }

    fun volumeDown() = CoroutineScope(Dispatchers.IO).launch {
        repeat(VOLUME_STEP) {
            rokuService.sendKeyAsync("VolumeDown").await()
        }
    }

    fun moveUp() = CoroutineScope(Dispatchers.IO).launch { rokuService.sendKeyAsync("Up") }

    fun moveDown() = CoroutineScope(Dispatchers.IO).launch { rokuService.sendKeyAsync("Down") }

    fun moveLeft() = CoroutineScope(Dispatchers.IO).launch { rokuService.sendKeyAsync("Left") }

    fun moveRight() = CoroutineScope(Dispatchers.IO).launch { rokuService.sendKeyAsync("Right") }

    fun okay() = CoroutineScope(Dispatchers.IO).launch { rokuService.sendKeyAsync("Select") }

    fun goHome() = CoroutineScope(Dispatchers.IO).launch { rokuService.sendKeyAsync("Home") }

    fun powerOff() = CoroutineScope(Dispatchers.IO).launch { rokuService.sendKeyAsync("PowerOff") }

    fun playPause() = CoroutineScope(Dispatchers.IO).launch { rokuService.sendKeyAsync("Play") }

    fun rewind() = CoroutineScope(Dispatchers.IO).launch { rokuService.sendKeyAsync("Rev") }

    fun fastForward() = CoroutineScope(Dispatchers.IO).launch { rokuService.sendKeyAsync("Fwd") }
}
