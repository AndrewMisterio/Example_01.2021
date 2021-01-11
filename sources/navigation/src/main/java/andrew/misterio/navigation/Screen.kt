package andrew.misterio.navigation

import android.os.Parcelable
import androidx.annotation.IdRes

interface Screen : Parcelable {
    @get:IdRes
    val actionId: Int
}
