package andrew.misterio.feature_details

import andrew.misterio.navigation.Router
import andrew.misterio.navigation.commands.Back
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailsViewModel(
    private val router: Router
): ViewModel() {

    val data: LiveData<DetailsData> get() = mutableData
    private val mutableData = MutableLiveData<DetailsData>()

    init {
        mutableData.value = DetailsData(
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/163.jpeg",
            title = "Ideal Jerry"
        )
    }

    fun onBackClick() {
        router.navigate(Back)
    }
}