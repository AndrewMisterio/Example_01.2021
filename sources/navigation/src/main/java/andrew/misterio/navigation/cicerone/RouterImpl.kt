package andrew.misterio.navigation.cicerone

import andrew.misterio.navigation.Command
import andrew.misterio.navigation.Router

internal class RouterImpl(
    private val router: CiceroneRouterImpl
): Router {
    override fun navigate(command: Command) = router.execute(CommandImpl(command))
}