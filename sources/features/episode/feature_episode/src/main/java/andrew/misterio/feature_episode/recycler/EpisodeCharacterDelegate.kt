package andrew.misterio.feature_episode.recycler

import andrew.misterio.feature_base.load
import andrew.misterio.feature_base.recycler.AdapterViewModel
import andrew.misterio.feature_base.recycler.dsl.delegate
import andrew.misterio.feature_episode.databinding.RecycleItemCharacterBinding

fun createEpisodeCharacterDelegate() = delegate<EpisodeCharacterAdapterModel, RecycleItemCharacterBinding>(
    binding = RecycleItemCharacterBinding::inflate
) {

    onBind {
        binding.ivEpisodeCharacterImage.load(it.imageUrl)
    }
}

data class EpisodeCharacterAdapterModel(
    val imageUrl: String
) : AdapterViewModel
