package andrew.misterio.domain_episode

import andrew.misterio.domain_episode.model.Episode

class GetEpisodeDataInteractor(
    private val repository: EpisodeDataRepository
) {
    suspend fun getData(id: Int): Episode = repository.loadEpisodeData(id)
}
