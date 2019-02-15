package us.timandkarli.betterrokuremote

import android.app.Application
import org.koin.android.ext.android.startKoin
import timber.log.Timber
import us.timandkarli.betterrokuremote.di.appModule
import us.timandkarli.betterrokuremote.di.netModule

class BetterRokuRemoteApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule, netModule))

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}