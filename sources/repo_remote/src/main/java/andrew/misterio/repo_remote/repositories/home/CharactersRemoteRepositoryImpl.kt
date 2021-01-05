package andrew.misterio.repo_remote.repositories.home

import CharactersQuery
import andrew.misterio.domain_home.CharactersRemoteRepository
import andrew.misterio.domain_home.models.Characters
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import org.koin.java.KoinJavaComponent.getKoin

class CharactersRemoteRepositoryImpl : CharactersRemoteRepository {

    private val apolloClient by getKoin().inject<ApolloClient>()

    override suspend fun getCharacterList(page: Int): Characters = apolloClient
        .query(CharactersQuery(Input.optional(page)))
        .await()
        .data
        ?.characters()
        .domainObject
}
