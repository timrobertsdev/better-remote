package dev.timroberts.simpleremote.network

import dev.timroberts.simpleremote.network.models.ActiveAppResponse
import dev.timroberts.simpleremote.network.models.DeviceInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RokuService {
    @GET("query/device-info")
    suspend fun getDeviceInfoAsync(): Response<DeviceInfoResponse>

    @GET("query/active-app")
    suspend fun getActiveAppAsync(): Response<ActiveAppResponse>

    @POST("keypress/{key}")
    suspend fun sendKeyAsync(@Path("key") key: String): Response<Unit>
}