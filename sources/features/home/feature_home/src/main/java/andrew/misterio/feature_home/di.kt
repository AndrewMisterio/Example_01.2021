package andrew.misterio.feature_home

import andrew.misterio.domain_home.CharactersRemoteRepository
import andrew.misterio.domain_home.GetCharactersInteractor
import andrew.misterio.repo_remote.repositories.home.CharactersRemoteRepositoryImpl
import org.koin.dsl.module

val featureHomeModule = module {
    scope<HomeFragment> {
        scoped { HomeViewModel(get(), get(), get()) }
        scoped<CharactersRemoteRepository> { CharactersRemoteRepositoryImpl() }
        scoped { GetCharactersInteractor(get()) }
    }
}
