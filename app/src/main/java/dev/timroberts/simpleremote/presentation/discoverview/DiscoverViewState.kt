package dev.timroberts.simpleremote.presentation.discoverview

import dev.timroberts.simpleremote.models.RokuDevice

sealed class DiscoverViewState{
    class Initializing : DiscoverViewState()
    class Discovering : DiscoverViewState()
    class Discovered(val rokuDevice: RokuDevice) : DiscoverViewState()
    class Finished : DiscoverViewState()
    class Cancelled : DiscoverViewState()
}
