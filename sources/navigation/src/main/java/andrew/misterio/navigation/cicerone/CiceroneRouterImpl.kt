package andrew.misterio.navigation.cicerone

import com.github.terrakok.cicerone.BaseRouter
import com.github.terrakok.cicerone.Command

internal class CiceroneRouterImpl : BaseRouter() {
    fun execute(command: Command) = executeCommands(command)
}
