package andrew.misterio.feature_home

import andrew.misterio.common.di_wrapper.CoroutineRunner
import andrew.misterio.domain_home.GetCharactersInteractor
import andrew.misterio.feature_base.recycler.AdapterViewModel
import andrew.misterio.feature_base.recycler.delegates.LoaderAdapterViewModel
import andrew.misterio.feature_home.adapterDelegates.CharacterAdapterViewModel
import andrew.misterio.navigation.Router
import andrew.misterio.navigation.commands.CloseApp
import andrew.misterio.navigation.commands.ToDetails
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arrow.syntax.function.andThen
import kotlinx.coroutines.delay

class HomeViewModel(
    private val getCharactersInteractor: GetCharactersInteractor,
    private val coroutineRunner: CoroutineRunner,
    private val router: Router
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

    fun onBackClick() {
        router.navigate(CloseApp)
    }

    fun reload() {
        val blankViewModel = CharacterAdapterViewModel.EMPTY
        mutableListData.value = List(20) { blankViewModel }
        getCharactersInteractor.resetPageCount()
        loadNextCharacters()
    }

    fun onLoaderVisible() = loadNextCharacters()

    private fun loadNextCharacters() {
        coroutineRunner.coroutine(
            body = {
                delay(2000)
                mutableListData.value = currentList.apply {
                    removeAll { it == CharacterAdapterViewModel.EMPTY || it is LoaderAdapterViewModel }
                    addAll(getCharactersInteractor.loadMoreCharacters().toViewObject)
                    add(LoaderAdapterViewModel)
                }
            }
        )
    }

    fun onItemClick(adapterViewModel: AdapterViewModel) {
        if(adapterViewModel is CharacterAdapterViewModel) {
            router.navigate(ToDetails(adapterViewModel.id))
        }
    }
}