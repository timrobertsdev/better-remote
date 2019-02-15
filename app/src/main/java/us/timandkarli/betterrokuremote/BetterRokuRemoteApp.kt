package us.timandkarli.betterrokuremote

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import org.koin.android.ext.android.startKoin
import timber.log.Timber
import us.timandkarli.betterrokuremote.di.appModule
import us.timandkarli.betterrokuremote.di.netModule

class BetterRokuRemoteApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) return
        LeakCanary.install(this)

        startKoin(this, listOf(appModule, netModule))

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}