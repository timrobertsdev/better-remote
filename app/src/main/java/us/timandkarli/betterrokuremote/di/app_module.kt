package us.timandkarli.betterrokuremote.di

import android.content.Context
import android.net.wifi.WifiManager
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import us.timandkarli.betterrokuremote.presentation.MainViewModel
import us.timandkarli.betterrokuremote.presentation.discoverview.DiscoverViewModel

val appModule = module {
    single { androidApplication().applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager }
    viewModel { DiscoverViewModel(get(), get(), get()) }
    viewModel { MainViewModel() }
}