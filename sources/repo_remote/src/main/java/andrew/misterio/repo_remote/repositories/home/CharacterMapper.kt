package andrew.misterio.repo_remote.repositories.home

import CharactersQuery
import andrew.misterio.domain_home.models.Character
import andrew.misterio.domain_home.models.Characters

val CharactersQuery.Characters?.domainObject: Characters
    get() =
        Characters(
            characters = this?.results()
                ?.mapNotNull { it -> it?.domainObject }
                .orEmpty(),
            nextPageNumber = this?.info()?.next()
        )

private val CharactersQuery.Result.domainObject: Character?
    get() {
        return Character(
            id = id()?.toIntOrNull() ?: return null,
            name = name()?.takeIf(String::isNotEmpty) ?: return null,
            imageUrl = image().orEmpty()
        )
    }