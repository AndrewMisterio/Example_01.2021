package andrew.misterio.feature_episode

import andrew.misterio.feature_base.recycler.AdapterViewModel

data class EpisodeViewDataModel(
    val title: String,
    val description: String,
    val characters: List<AdapterViewModel>
)
