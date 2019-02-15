package us.timandkarli.betterrokuremote.presentation.discoverview

import us.timandkarli.betterrokuremote.models.RokuDevice

data class DiscoverViewState(
    val state: DiscoverState = DiscoverState.INIT,
    val data: RokuDevice? = null
)

enum class DiscoverState {
    INIT, DISCOVERING, FINISHED, CANCELLED
}