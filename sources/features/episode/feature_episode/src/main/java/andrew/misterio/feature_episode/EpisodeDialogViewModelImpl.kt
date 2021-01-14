package andrew.misterio.feature_episode

import andrew.misterio.common.di_wrapper.listOf
import andrew.misterio.feature_episode.recycler.EpisodeCharacterAdapterModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EpisodeDialogViewModelImpl : ViewModel(), EpisodeDialogViewModel {
    override val data: MutableLiveData<EpisodeViewDataModel> = MutableLiveData()

    init {
        val mock = EpisodeCharacterAdapterModel("https://rickandmortyapi.com/api/character/avatar/343.jpeg")
        data.value = EpisodeViewDataModel(listOf { repeat(20) { add(mock) } })
    }
}
