package andrew.misterio.feature_home

import andrew.misterio.common.di_wrapper.CoroutineRunner
import andrew.misterio.domain_home.GetCharactersInteractor
import andrew.misterio.feature_base.recycler.AdapterViewModel
import andrew.misterio.feature_base.recycler.delegates.LoaderAdapterViewModel
import andrew.misterio.feature_home.adapterDelegates.PersonAdapterViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arrow.syntax.function.andThen

class HomeViewModel(
    private val getCharactersInteractor: GetCharactersInteractor,
    private val coroutineRunner: CoroutineRunner
) : ViewModel() {
    private val mutableListData = MutableLiveData<List<AdapterViewModel>>()
    val listData: LiveData<List<AdapterViewModel>> get() = mutableListData

    private val currentList: MutableList<AdapterViewModel>
        get() =
            mutableListData.value?.toMutableList()
                ?: run(getCharactersInteractor::resetPageCount andThen { mutableListOf() })

    init {
        reload()
    }

    fun reload() {
        mutableListData.value = List(20) { PersonAdapterViewModel.EMPTY }
        getCharactersInteractor.resetPageCount()
        loadNextCharacters()
    }

    fun onLoaderVisible() = loadNextCharacters()

    private fun loadNextCharacters() {
        coroutineRunner.coroutine(
            body = {
                mutableListData.value = currentList.apply {
                    removeAll { it == PersonAdapterViewModel.EMPTY || it is LoaderAdapterViewModel }
                    addAll(getCharactersInteractor.loadMoreCharacters().toViewObject)
                    add(LoaderAdapterViewModel)
                }
            }
        )
    }


    fun onItemClick(adapterViewModel: AdapterViewModel) {
        // TODO navigation to details
    }
}