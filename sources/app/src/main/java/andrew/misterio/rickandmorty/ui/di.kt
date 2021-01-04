package andrew.misterio.rickandmorty.ui

import andrew.misterio.feature_base.navigation.Navigator
import andrew.misterio.rickandmorty.navigation.NavigatorImpl
import androidx.navigation.NavController
import org.koin.dsl.module

fun activityModule(navController: () -> NavController) = module {
    scope<MainActivity> {
        scoped<Navigator> { NavigatorImpl(navController()) }
    }
}
