package andrew.misterio.feature_home

import andrew.misterio.common.di_wrapper.CoroutineRunner
import andrew.misterio.domain_home.GetCharactersInteractor
import andrew.misterio.feature_base.recycler.AdapterViewModel
import andrew.misterio.feature_home.adapterDelegates.PersonAdapterViewModel
import andrew.misterio.feature_home.adapterDelegates.RowAdapterViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel(
    private val getCharactersInteractor: GetCharactersInteractor,
    coroutineRunner: CoroutineRunner
) : ViewModel() {
    private val mutableListData = MutableLiveData<List<AdapterViewModel>>()
    val listData: LiveData<List<AdapterViewModel>> get() = mutableListData

    init {
        coroutineRunner.coroutine(
            body = {
                mutableListData.value = getCharactersInteractor.loadMoreCharacters().toViewObject
            }
        )
    }

    fun onItemClick(adapterViewModel: AdapterViewModel) {
        // TODO navigation to details
    }
}