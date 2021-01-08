package andrew.misterio.navigation.cicerone

import andrew.misterio.navigation.NavControllerOwner
import andrew.misterio.navigation.R
import andrew.misterio.navigation.commands.Back
import andrew.misterio.navigation.commands.CloseApp
import andrew.misterio.navigation.commands.ToDetails
import andrew.misterio.navigation.commands.ToHome
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator

internal class NavigatorImpl(
    private val controllerOwner: NavControllerOwner
): Navigator {

    override fun applyCommands(commands: Array<out Command>) {
        commands.forEach { if(it is CommandImpl) applyCommand(it.command) }
    }

    private fun applyCommand(command: andrew.misterio.navigation.Command) {
        val controller = controllerOwner.navController?:return
        when(command) {
            is Back -> controller.navigateUp()
            is ToHome -> controller.navigate(R.id.navigation_to_home)
            is ToDetails -> controller.navigate(R.id.navigation_to_details)
            is CloseApp -> controllerOwner.closeActivity()
            else -> {
                // Do nothing
            }
        }
    }
}
