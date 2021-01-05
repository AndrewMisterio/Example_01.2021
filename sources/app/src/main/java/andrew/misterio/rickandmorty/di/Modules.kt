package andrew.misterio.rickandmorty.di

import andrew.misterio.common.di_wrapper.CoroutineRunner
import andrew.misterio.rickandmorty.BuildConfig
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module

val appModule = module {
    single<CoroutineRunner> { CoroutineRunner() }
    single {
        ChuckerInterceptor
            .Builder(get())
            .build()
    }
    single<OkHttpClient.Builder> {
        OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(get<ChuckerInterceptor>())
                }
            }
    }
}
