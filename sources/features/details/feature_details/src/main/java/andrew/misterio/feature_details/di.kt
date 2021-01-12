package andrew.misterio.feature_details

import andrew.misterio.domain_details.GetDetailsInteractor
import andrew.misterio.domain_details.repo.DetailsRepo
import andrew.misterio.repo_remote.repositories.character.DetailsRepoImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureDetailsModule = module {
    scope<DetailsFragment> {
        viewModel { (id: Int) -> DetailsViewModel(id, get(), get(), get()) }
        scoped { GetDetailsInteractor(get()) }
        scoped<DetailsRepo> { DetailsRepoImpl() }
    }
}
