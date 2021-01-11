package andrew.misterio.feature_base

import android.content.Context

val Context.screenHeight: Int get() = resources.displayMetrics.heightPixels
val Context.screenWidth: Int get() = resources.displayMetrics.widthPixels
val Context.isPortrait: Boolean get() = screenWidth < screenHeight
