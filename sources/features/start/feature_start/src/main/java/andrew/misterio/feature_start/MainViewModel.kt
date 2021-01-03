package andrew.misterio.feature_start

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

internal class MainViewModel(
    private val name: String
) : ViewModel() {
    val data by lazy { MutableLiveData(name) }
}
