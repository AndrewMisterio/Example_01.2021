package andrew.misterio.feature_home

import org.koin.dsl.module

val featureHomeModule = module {
    scope<HomeFragment> {
        scoped { HomeViewModel() }
    }
}
