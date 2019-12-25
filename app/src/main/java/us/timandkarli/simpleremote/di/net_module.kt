package us.timandkarli.simpleremote.di

import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import us.timandkarli.simpleremote.network.HostSelectionInterceptor
import us.timandkarli.simpleremote.network.RokuService
import java.util.concurrent.TimeUnit

val netModule = module {
    single { HostSelectionInterceptor() }
    single { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }
    single {
        OkHttpClient.Builder()
            .callTimeout(3, TimeUnit.SECONDS)
            .addInterceptor(get<HostSelectionInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    single {
        Retrofit.Builder()
            .addConverterFactory(TikXmlConverterFactory.create())
            .baseUrl("http://127.0.0.1:8060")
            .client(get())
            .build()
    }
    single { get<Retrofit>().create(RokuService::class.java) }
}