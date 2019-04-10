package us.timandkarli.betterrokuremote.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import us.timandkarli.betterrokuremote.presentation.MainViewModel
import us.timandkarli.betterrokuremote.presentation.discoverview.DiscoverViewModel
import us.timandkarli.betterrokuremote.presentation.remoteview.RemoteViewModel

private const val SHARED_PREFS_FILENAME = "us.timandkarli.weightlossstatistics"

val appModule = module {
    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { DiscoverViewModel(get(), get()) }
    viewModel { RemoteViewModel(get()) }

    single { androidContext().getSharedPreferences(SHARED_PREFS_FILENAME, Context.MODE_PRIVATE) }
}