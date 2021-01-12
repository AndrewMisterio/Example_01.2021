package andrew.misterio.repo_remote.repositories.character

import CharacterQuery
import andrew.misterio.domain_details.models.Details
import andrew.misterio.domain_details.repo.DetailsRepo
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import org.koin.java.KoinJavaComponent

class DetailsRepoImpl : DetailsRepo {
    private val apolloClient by KoinJavaComponent.getKoin().inject<ApolloClient>()
    override suspend fun getDetails(id: Int): Details = apolloClient
        .query(CharacterQuery(id.toString()))
        .await()
        .data
        ?.character()
        .domain
}
