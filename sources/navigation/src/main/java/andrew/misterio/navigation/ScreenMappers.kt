package andrew.misterio.navigation

import android.os.Bundle
import androidx.annotation.IdRes

interface ScreenMappers {
    @IdRes
    fun getActionId(screen: Screen): Int

    fun getArgsBundle(screen: Screen): Bundle?
}
