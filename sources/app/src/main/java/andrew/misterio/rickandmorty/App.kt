package andrew.misterio.rickandmorty

import andrew.misterio.feature_home.featureHomeModule
import andrew.misterio.feature_start.featureStartModule
import andrew.misterio.repo_remote.repoRemoteModule
import andrew.misterio.rickandmorty.di.appModule
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
                featureStartModule,
                featureHomeModule,
                repoRemoteModule
            )
        }
    }
}
