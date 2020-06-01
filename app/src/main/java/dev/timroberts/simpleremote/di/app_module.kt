package dev.timroberts.simpleremote.di

import android.content.Context
import dev.timroberts.simpleremote.presentation.MainViewModel
import dev.timroberts.simpleremote.presentation.discoverview.DiscoverViewModel
import dev.timroberts.simpleremote.presentation.remoteview.RemoteViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val SHARED_PREFS_FILENAME = "dev.timroberts.simpleremote"

val appModule = module {
    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { DiscoverViewModel(get(), get()) }
    viewModel { RemoteViewModel(get()) }

    single { androidContext().getSharedPreferences(SHARED_PREFS_FILENAME, Context.MODE_PRIVATE) }
}