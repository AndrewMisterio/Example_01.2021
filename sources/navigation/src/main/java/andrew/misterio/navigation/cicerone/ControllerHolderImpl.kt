package andrew.misterio.navigation.cicerone

import andrew.misterio.navigation.ControllerOwnerHolder
import andrew.misterio.navigation.NavControllerOwner
import androidx.lifecycle.LifecycleOwner
import arrow.core.andThen
import com.github.terrakok.cicerone.NavigatorHolder

internal class ControllerHolderImpl(
    private val holder: NavigatorHolder
): ControllerOwnerHolder {

    override fun onPause(source: LifecycleOwner) {
        holder.removeNavigator()
    }

    override fun onResume(source: LifecycleOwner) {
        (source as? NavControllerOwner)?.let(::NavigatorImpl andThen holder::setNavigator)
    }
}
