package us.timandkarli.simpleremote

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import us.timandkarli.simpleremote.di.appModule
import us.timandkarli.simpleremote.di.netModule

class SimpleRemoteApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SimpleRemoteApp)
            androidFileProperties()
            modules(listOf(appModule, netModule))
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}