package andrew.misterio.feature_base

import android.graphics.drawable.Drawable
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes

interface Resources {
    fun getInteger(@IntegerRes id: Int): Int
    fun getPixelsSize(@DimenRes id: Int): Int
    fun getString(@StringRes id: Int, vararg vals: Any): String
    fun getDrawable(@DrawableRes id: Int): Drawable?
}