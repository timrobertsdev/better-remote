package us.timandkarli.betterrokuremote.presentation.remoteview

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import us.timandkarli.betterrokuremote.network.RokuService

class RemoteViewModel(private val rokuService: RokuService) : ViewModel() {
    fun volumeUp(step: Int) = CoroutineScope(Dispatchers.IO).launch {
        // TODO: Error handling for all sendkey commands, possibly all calls
        repeat(step) {
            rokuService.sendKeyAsync("VolumeUp").await()
        }
    }

    fun volumeDown(step: Int) = CoroutineScope(Dispatchers.IO).launch {
        repeat(step) {
            rokuService.sendKeyAsync("VolumeDown").await()
        }
    }

    fun moveUp() = CoroutineScope(Dispatchers.IO).launch { rokuService.sendKeyAsync("Up") }

    fun moveDown() = CoroutineScope(Dispatchers.IO).launch { rokuService.sendKeyAsync("Down") }

    fun moveLeft() = CoroutineScope(Dispatchers.IO).launch { rokuService.sendKeyAsync("Left") }

    fun moveRight() = CoroutineScope(Dispatchers.IO).launch { rokuService.sendKeyAsync("Right") }

    fun okay() = CoroutineScope(Dispatchers.IO).launch { rokuService.sendKeyAsync("Select") }
}
