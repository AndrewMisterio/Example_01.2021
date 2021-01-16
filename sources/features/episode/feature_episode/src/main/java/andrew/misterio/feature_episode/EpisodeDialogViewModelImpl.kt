package andrew.misterio.feature_episode

import andrew.misterio.common.di_wrapper.CoroutineRunner
import andrew.misterio.domain_episode.GetEpisodeDataInteractor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EpisodeDialogViewModelImpl(
    private val id: Int,
    private val interactor: GetEpisodeDataInteractor,
    runner: CoroutineRunner
) : ViewModel(), EpisodeDialogViewModel {

    override val data = MutableLiveData<EpisodeViewDataModel>()

    init {
        runner.coroutine(
            body = {
                data.value = interactor.getData(id).viewData
            }
        )
    }
}
