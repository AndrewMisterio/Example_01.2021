package andrew.misterio.feature_details

import andrew.misterio.common.di_wrapper.CoroutineRunner
import andrew.misterio.domain_details.GetDetailsInteractor
import andrew.misterio.domain_details.models.Episode
import andrew.misterio.feature_details.recycler_delegates.DetailsListAdapterModel
import andrew.misterio.navigation.Router
import andrew.misterio.navigation.commands.Back
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arrow.core.andThen

class DetailsViewModel(
    id: Int,
    private val router: Router,
    private val coroutineRunner: CoroutineRunner,
    private val interactor: GetDetailsInteractor
) : ViewModel() {

    val viewData: LiveData<DetailsViewData> get() = mutableData
    private val mutableData = MutableLiveData<DetailsViewData>()

    init {
        coroutineRunner.coroutine(
            body = {
                mutableData.value = interactor.getDetails(id).let {
                    DetailsViewData(
                        id = id,
                        imageUrl = it.imageUrl,
                        title = it.title,
                        list = it.list.map(Episode::title andThen ::DetailsListAdapterModel)
                    )
                }
            }
        )
    }

    fun onBackClick() {
        router.navigate(Back)
    }
}
