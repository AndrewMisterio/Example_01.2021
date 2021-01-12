package andrew.misterio.feature_start

import andrew.misterio.feature_base.Resources
import andrew.misterio.feature_base.navigation.screens.ToHome
import andrew.misterio.navigation.Router
import andrew.misterio.navigation.commands.Forward
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class StartViewModel(
    private val router: Router,
    private val resources: Resources
) : ViewModel() {
    private val animDuration by lazy { resources.getInteger(R.integer.title_animation_duration).toLong() }

    val data: LiveData<ViewModelData> by lazy {
        MutableLiveData(
            ViewModelData(
                animationDuration = animDuration,
                mainImageUrl = "https://m.media-amazon.com/images/M/MV5BZjRjOTFkOTktZWUzMi00YzMyLThkMmYtMjEwNmQyNzliYTNmXkEyXkFqcGdeQXVyNzQ1ODk3MTQ@._V1_.jpg"
            )
        )
    }

    init {
        CoroutineScope(Dispatchers.Main).launch {
            delay(animDuration * 2)
            router.navigate(Forward(ToHome))
        }
    }
}
