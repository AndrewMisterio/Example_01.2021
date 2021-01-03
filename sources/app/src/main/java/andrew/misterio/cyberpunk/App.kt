package andrew.misterio.cyberpunk

import andrew.misterio.cyberpunk.di.appModule
import andrew.misterio.feature_start.FeatureMain
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                appModule,
                FeatureMain.module
            )
        }
    }
}