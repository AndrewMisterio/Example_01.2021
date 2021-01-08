package andrew.misterio.feature_start

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureStartModule = module {
    scope<StartFragment> {
        viewModel { StartViewModel(get()) }
    }
}
