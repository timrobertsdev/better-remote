package us.timandkarli.betterrokuremote.network

import okhttp3.Interceptor
import okhttp3.Response

class HostSelectionInterceptor : Interceptor {
    @Volatile
    var host: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val host = this.host
        if (host != null) {
            val newUrl = request.url().newBuilder()
                .port(8060)
                .host(host)
                .build()
            request = request.newBuilder()
                .url(newUrl)
                .build()
        }
        return chain.proceed(request)
    }
}