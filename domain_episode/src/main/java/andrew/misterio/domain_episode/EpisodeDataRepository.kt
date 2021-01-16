package andrew.misterio.domain_episode

import andrew.misterio.domain_episode.model.Episode

interface EpisodeDataRepository {
    suspend fun loadEpisodeData(id: Int): Episode
}
