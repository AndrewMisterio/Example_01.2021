package andrew.misterio.feature_home

import andrew.misterio.feature_base.recycler.AdapterViewModel
import andrew.misterio.feature_home.adapterDelegates.PersonAdapterViewModel
import andrew.misterio.feature_home.adapterDelegates.RowAdapterViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val mutableListData = MutableLiveData<List<AdapterViewModel>>()
    val listData: LiveData<List<AdapterViewModel>> get() = mutableListData

    init {
        mutableListData.value = listOf(
            RowAdapterViewModel(
                title = "Characters",
                items = listOf(
                    PersonAdapterViewModel(
                        imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                        title = "Rick Sanchez"
                    )
                )
            )
        )
    }

    fun onItemClick(adapterViewModel: AdapterViewModel) {
        // TODO navigation ti details
    }
}