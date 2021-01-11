package andrew.misterio.navigation.cicerone

import andrew.misterio.navigation.Router
import andrew.misterio.navigation.jetpack.ControllerOwnerHolder
import com.github.terrakok.cicerone.Cicerone
import org.koin.dsl.ScopeDSL

val navigationCiceroneDefinitions: ScopeDSL.() -> Unit = {
    val cicerone = Cicerone.create(CiceroneRouterImpl())
    scoped<Router> { RouterImpl(cicerone.router) }
    scoped<ControllerOwnerHolder> { ControllerHolderImpl(cicerone.getNavigatorHolder(), get()) }
}
