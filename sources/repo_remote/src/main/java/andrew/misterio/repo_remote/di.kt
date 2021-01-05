package andrew.misterio.repo_remote

import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient
import org.koin.dsl.module

private const val BASE_URL = "https://rickandmortyapi.com/graphql"

val repoRemoteModule = module {
    single<OkHttpClient> {
        get<OkHttpClient.Builder>().build()
    }
    single<ApolloClient> {
        ApolloClient
            .builder()
            //TODO Change to params from file.properties
            .serverUrl(BASE_URL)
            .okHttpClient(get())
            .build()

    }
}

