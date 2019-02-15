package us.timandkarli.betterrokuremote.presentation.discoverview

import us.timandkarli.betterrokuremote.models.RokuDevice

sealed class DiscoverViewState{
    class Initializing : DiscoverViewState()
    class Discovering : DiscoverViewState()
    class Discovered(val rokuDevice: RokuDevice) : DiscoverViewState()
    class Finished : DiscoverViewState()
    class Cancelled : DiscoverViewState()
}
