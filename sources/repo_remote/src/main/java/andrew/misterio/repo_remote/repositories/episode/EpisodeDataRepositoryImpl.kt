package andrew.misterio.repo_remote.repositories.episode

import EpisodeQuery
import andrew.misterio.domain_episode.EpisodeDataRepository
import andrew.misterio.domain_episode.model.Episode
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import org.koin.java.KoinJavaComponent

class EpisodeDataRepositoryImpl : EpisodeDataRepository {

    private val apolloClient by KoinJavaComponent.getKoin().inject<ApolloClient>()

    override suspend fun loadEpisodeData(id: Int): Episode = apolloClient
        .query(EpisodeQuery(id.toString()))
        .await()
        .data
        ?.episode()
        .domain
}
