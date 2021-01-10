package andrew.misterio.navigation.cicerone

import andrew.misterio.navigation.NavControllerOwner
import andrew.misterio.navigation.ScreenMappers
import andrew.misterio.navigation.commands.Back
import andrew.misterio.navigation.commands.CloseApp
import andrew.misterio.navigation.commands.Forward
import androidx.navigation.NavController
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import org.koin.java.KoinJavaComponent.getKoin

internal class NavigatorImpl(
    private val controllerOwner: NavControllerOwner,
    private val screenMappers: ScreenMappers
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

    private fun NavController.forward(command: Forward) {
        navigate(
            screenMappers.getActionId(command.screen),
            screenMappers.getArgsBundle(command.screen),
            null,
            command.extras
        )
    }
}
