package andrew.misterio.feature_details

import andrew.misterio.common.di_wrapper.CoroutineRunner
import andrew.misterio.domain_details.GetDetailsInteractor
import andrew.misterio.domain_details.models.Details
import andrew.misterio.feature_base.navigation.screens.ToEpisode
import andrew.misterio.feature_base.recycler.AdapterViewModel
import andrew.misterio.feature_details.recycler_delegates.DetailsListAdapterModel
import andrew.misterio.navigation.Router
import andrew.misterio.navigation.commands.Back
import andrew.misterio.navigation.commands.Forward
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arrow.syntax.function.invoke

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
                mutableData.value = interactor.getDetails(id)
                    .let(Details::toViewData.invoke(p2 = id))
            }
        )
    }

    fun onListItemClick(item: AdapterViewModel) {
        when (item) {
            is DetailsListAdapterModel -> {
                router.navigate(Forward(screen = ToEpisode(item.id)))
            }
        }
    }

    fun onBackClick() {
        router.navigate(Back)
    }
}
