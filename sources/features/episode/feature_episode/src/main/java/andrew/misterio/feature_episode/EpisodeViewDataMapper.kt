package andrew.misterio.feature_episode

import andrew.misterio.domain_episode.model.Character
import andrew.misterio.domain_episode.model.Episode
import andrew.misterio.feature_episode.recycler.EpisodeCharacterAdapterModel

internal val Episode.viewData: EpisodeViewDataModel get() = EpisodeViewDataModel(
    characters = characters.map(Character::adapterViewData),
    title = name,
    description = description
)

private val Character.adapterViewData get() = EpisodeCharacterAdapterModel(
    id = id,
    imageUrl = imageUrl
)
