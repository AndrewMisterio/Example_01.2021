package andrew.misterio.feature_details

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureDetailsModule = module {
    scope<DetailsFragment> {
        viewModel { DetailsViewModel(get()) }
    }
}