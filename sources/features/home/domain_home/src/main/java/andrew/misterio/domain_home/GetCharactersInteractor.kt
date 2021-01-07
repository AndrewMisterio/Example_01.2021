package andrew.misterio.domain_home

import andrew.misterio.domain_home.models.Character

class GetCharactersInteractor(
    private val charactersRemoteRepository: CharactersRemoteRepository
) {
    private var nextPageNumber: Int? = INIT_PAGE_NUMBER

    val hasNextItems: Boolean get() = nextPageNumber != null

    fun resetPageCount() {
        nextPageNumber = INIT_PAGE_NUMBER
    }

    suspend fun loadMoreCharacters(): List<Character> = when (val number = nextPageNumber) {
        null -> emptyList()
        else -> loadMoreCharacters(number)
    }

    private suspend fun loadMoreCharacters(number: Int) =
        charactersRemoteRepository.getCharacterList(number)
            .let { result ->
                nextPageNumber = result.nextPageNumber
                result.characters
            }

    companion object {
        private const val INIT_PAGE_NUMBER = 1
    }
}