package andrew.misterio.feature_start

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object FeatureMain {
    val module = module {
        scope<MainFragment> {
            viewModel { (id: String) -> MainViewModel(id) }
        }
    }
    val root get() = MainFragment.newInstance()
}