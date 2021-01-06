package andrew.misterio.rickandmorty.di

import andrew.misterio.common.di_wrapper.CoroutineRunner
import andrew.misterio.feature_base.Resources
import andrew.misterio.rickandmorty.BuildConfig
import andrew.misterio.rickandmorty.utils.ResourcesImpl
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module

val appModule = module {
    single<Resources> { ResourcesImpl(get()) }
    single { CoroutineRunner() }
    single {
        ChuckerInterceptor
            .Builder(get())
            .build()
    }
    single {
        OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(get<ChuckerInterceptor>())
                }
            }
    }
}
