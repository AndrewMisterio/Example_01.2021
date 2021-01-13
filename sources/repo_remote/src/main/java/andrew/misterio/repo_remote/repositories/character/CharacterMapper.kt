package andrew.misterio.repo_remote.repositories.character

import CharacterQuery
import andrew.misterio.domain_details.models.Details
import andrew.misterio.domain_details.models.Episode
import arrow.syntax.function.invoke

val CharacterQuery.Character?.domain: Details
    get() = Details(
        imageUrl = this?.image().orEmpty(),
        title = this?.name().orEmpty(),
        list = this?.episode()?.mapNotNull(::mapEpisode).orEmpty()
    )

private fun mapEpisode(episodeApiModel: CharacterQuery.Episode): Episode? {
    return Episode(
        id = episodeApiModel.id()?.toIntOrNull() ?: return null,
        title = "${episodeApiModel.episode()}: '${episodeApiModel.name()}'"
    )
}
