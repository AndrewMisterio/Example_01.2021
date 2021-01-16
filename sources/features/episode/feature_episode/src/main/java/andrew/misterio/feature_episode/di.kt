package andrew.misterio.feature_episode

import andrew.misterio.domain_episode.EpisodeDataRepository
import andrew.misterio.domain_episode.GetEpisodeDataInteractor
import andrew.misterio.repo_remote.repositories.episode.EpisodeDataRepositoryImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val episodeModule = module {
    scope<EpisodeDialogFragment> {
        viewModel { (id: Int) -> EpisodeDialogViewModelImpl(id, get(), get()) }
        scoped<EpisodeDataRepository> { EpisodeDataRepositoryImpl() }
        scoped { GetEpisodeDataInteractor(get()) }
    }
}
