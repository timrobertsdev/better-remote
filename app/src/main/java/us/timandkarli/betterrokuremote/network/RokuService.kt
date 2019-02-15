package us.timandkarli.betterrokuremote.network

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import us.timandkarli.betterrokuremote.network.models.ActiveAppResponse
import us.timandkarli.betterrokuremote.network.models.DeviceInfoResponse

interface RokuService {
    @GET("query/device-info")
    fun getDeviceInfoAsync(): Deferred<DeviceInfoResponse>

    @GET("query/active-app")
    fun getActiveAppAsync(): Deferred<ActiveAppResponse>

    @POST("keypress/{key}")
    fun sendKeyAsync(@Path("key") key: String): Deferred<Response<Unit>>
}