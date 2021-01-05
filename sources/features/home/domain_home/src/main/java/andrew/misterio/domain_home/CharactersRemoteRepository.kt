package andrew.misterio.domain_home

import andrew.misterio.domain_home.models.Characters

interface CharactersRemoteRepository {
    suspend fun getCharacterList(page: Int): Characters
}