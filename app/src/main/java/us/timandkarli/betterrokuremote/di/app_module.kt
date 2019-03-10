package us.timandkarli.betterrokuremote.di

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import us.timandkarli.betterrokuremote.presentation.MainViewModel
import us.timandkarli.betterrokuremote.presentation.discoverview.DiscoverViewModel
import us.timandkarli.betterrokuremote.presentation.remoteview.RemoteViewModel

val appModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { DiscoverViewModel(get(), get()) }
    viewModel { RemoteViewModel(get()) }
}