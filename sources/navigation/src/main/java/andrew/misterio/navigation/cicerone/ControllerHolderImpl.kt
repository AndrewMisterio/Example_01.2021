package andrew.misterio.navigation.cicerone

import andrew.misterio.navigation.ControllerOwnerHolder
import andrew.misterio.navigation.NavControllerOwner
import andrew.misterio.navigation.ScreenMappers
import androidx.lifecycle.LifecycleOwner
import com.github.terrakok.cicerone.NavigatorHolder

internal class ControllerHolderImpl(
    private val holder: NavigatorHolder,
    private val mapper: ScreenMappers
): ControllerOwnerHolder {

    override fun onPause(source: LifecycleOwner) {
        holder.removeNavigator()
    }

    override fun onResume(source: LifecycleOwner) {
        holder.setNavigator(
            NavigatorImpl(
                controllerOwner = source as? NavControllerOwner?:return,
                screenMappers = mapper
            )
        )
    }
}
