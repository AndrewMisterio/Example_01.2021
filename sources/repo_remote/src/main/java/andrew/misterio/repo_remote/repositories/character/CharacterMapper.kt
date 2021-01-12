package andrew.misterio.repo_remote.repositories.character

import CharacterQuery
import andrew.misterio.domain_details.models.Details
import andrew.misterio.domain_details.models.Episode
import arrow.syntax.function.invoke

val CharacterQuery.Character?.domain: Details
    get() = Details(
        imageUrl = this?.image().orEmpty(),
        title = this?.name().orEmpty(),
        list = this?.episode()?.mapNotNull(CharacterQuery.Episode::name)
            ?.map(::Episode.invoke(p2 = listOf())).orEmpty()
    )
