package us.timandkarli.betterrokuremote.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import us.timandkarli.betterrokuremote.network.models.ActiveAppResponse
import us.timandkarli.betterrokuremote.network.models.DeviceInfoResponse

interface RokuService {
    @GET("query/device-info")
    fun getDeviceInfo(): Deferred<DeviceInfoResponse>

    @GET("query/active-app")
    fun getActiveApp(): Deferred<ActiveAppResponse>
}