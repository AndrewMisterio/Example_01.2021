package andrew.misterio.rickandmorty.ui

import andrew.misterio.navigation.ScreenMappers
import andrew.misterio.navigation.cicerone.navigationCiceroneDefinitions
import andrew.misterio.rickandmorty.ui.navigation.ScreenMappersImpl
import org.koin.dsl.module

val mainModule = module {
    scope<MainActivity> {
        navigationCiceroneDefinitions()
        scoped<ScreenMappers> { ScreenMappersImpl() }
    }
}
