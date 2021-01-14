package andrew.misterio.rickandmorty

import andrew.misterio.feature_details.featureDetailsModule
import andrew.misterio.feature_episode.episodeModule
import andrew.misterio.feature_home.featureHomeModule
import andrew.misterio.feature_start.featureStartModule
import andrew.misterio.repo_remote.repoRemoteModule
import andrew.misterio.rickandmorty.di.appModule
import andrew.misterio.rickandmorty.ui.mainModule
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
                mainModule,
                featureStartModule,
                featureHomeModule,
                repoRemoteModule,
                featureDetailsModule,
                episodeModule
            )
        }
    }
}
