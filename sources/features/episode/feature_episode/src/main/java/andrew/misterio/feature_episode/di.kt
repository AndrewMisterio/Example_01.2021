package andrew.misterio.feature_episode

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val episodeModule = module {
    scope<EpisodeDialogFragment> {
        viewModel { EpisodeDialogViewModelImpl() }
    }
}
