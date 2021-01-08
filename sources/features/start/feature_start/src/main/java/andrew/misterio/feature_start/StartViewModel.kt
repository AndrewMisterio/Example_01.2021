package andrew.misterio.feature_start

import andrew.misterio.navigation.Router
import andrew.misterio.navigation.commands.ToHome
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class StartViewModel(
    private val router: Router
) : ViewModel() {
    val data: LiveData<ViewModelData> by lazy {
        MutableLiveData(
            ViewModelData(
                mainImageUrl = "https://m.media-amazon.com/images/M/MV5BZjRjOTFkOTktZWUzMi00YzMyLThkMmYtMjEwNmQyNzliYTNmXkEyXkFqcGdeQXVyNzQ1ODk3MTQ@._V1_.jpg"
            )
        )
    }

    init {
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            router.navigate(ToHome)
        }
    }
}
