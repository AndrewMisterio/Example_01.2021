package andrew.misterio.repo_remote.repositories.episode

import EpisodeQuery
import andrew.misterio.domain_episode.model.Character
import andrew.misterio.domain_episode.model.Episode

val EpisodeQuery.Episode?.domain: Episode
    get() = Episode(
        name = this?.name().orEmpty(),
        description = createDescription(this?.episode().orEmpty(), this?.air_date().orEmpty()),
        characters = this?.characters()?.mapNotNull(EpisodeQuery.Character::domain).orEmpty()
    )

private fun createDescription(episode: String?, airDate: String?): String = when {
    episode == null && airDate != null -> airDate
    episode != null && airDate == null -> episode
    episode != null && airDate != null -> "$episode - $airDate"
    else -> ""
}

private val EpisodeQuery.Character.domain: Character?
    get() {
        return Character(
            id = id()?.toIntOrNull() ?: return null,
            imageUrl = image().orEmpty()
        )
    }
