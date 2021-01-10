package andrew.misterio.feature_details

import andrew.misterio.navigation.Router
import andrew.misterio.navigation.commands.Back
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailsViewModel(
    id: Int,
    private val router: Router
): ViewModel() {

    val data: LiveData<DetailsData> get() = mutableData
    private val mutableData = MutableLiveData<DetailsData>()

    init {
        mutableData.value = DetailsData(
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/$id.jpeg",
            title = "Ideal Jerry #$id"
        )
    }

    fun onBackClick() {
        router.navigate(Back)
    }
}