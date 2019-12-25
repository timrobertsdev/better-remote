package us.timandkarli.simpleremote.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import us.timandkarli.simpleremote.network.models.ActiveAppResponse
import us.timandkarli.simpleremote.network.models.DeviceInfoResponse

interface RokuService {
    @GET("query/device-info")
    suspend fun getDeviceInfoAsync(): DeviceInfoResponse

    @GET("query/active-app")
    suspend fun getActiveAppAsync(): ActiveAppResponse

    @POST("keypress/{key}")
    suspend fun sendKeyAsync(@Path("key") key: String): Response<Unit>
}