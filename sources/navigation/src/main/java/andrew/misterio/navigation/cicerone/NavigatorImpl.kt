package andrew.misterio.navigation.cicerone

import andrew.misterio.navigation.NAVIGATION_ARG_KEY
import andrew.misterio.navigation.commands.Back
import andrew.misterio.navigation.commands.CloseApp
import andrew.misterio.navigation.commands.Forward
import andrew.misterio.navigation.jetpack.NavControllerOwner
import android.os.Bundle
import androidx.navigation.NavController
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator

internal class NavigatorImpl(
    private val controllerOwner: NavControllerOwner
) : Navigator {

    override fun applyCommands(commands: Array<out Command>) {
        commands.forEach { if (it is CommandImpl) applyCommand(it.command) }
    }

    private fun applyCommand(command: andrew.misterio.navigation.Command) {
        val controller = controllerOwner.navController ?: return
        when (command) {
            is Back -> controller.navigateUp()
            is Forward -> controller.forward(command)
            is CloseApp -> controllerOwner.closeActivity()
            else -> {
                // Do nothing
            }
        }
    }

    private fun NavController.forward(command: Forward) = navigate(
        command.screen.actionId,
        Bundle().apply { putParcelable(NAVIGATION_ARG_KEY, command.screen) },
        null,
        command.extras
    )
}
