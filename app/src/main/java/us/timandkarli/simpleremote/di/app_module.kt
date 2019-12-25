package us.timandkarli.simpleremote.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import us.timandkarli.simpleremote.presentation.MainViewModel
import us.timandkarli.simpleremote.presentation.discoverview.DiscoverViewModel
import us.timandkarli.simpleremote.presentation.remoteview.RemoteViewModel

private const val SHARED_PREFS_FILENAME = "us.timandkarli.weightlossstatistics"

val appModule = module {
    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { DiscoverViewModel(get(), get()) }
    viewModel { RemoteViewModel(get()) }

    single { androidContext().getSharedPreferences(SHARED_PREFS_FILENAME, Context.MODE_PRIVATE) }
}