package andrew.misterio.rickandmorty.ui

import andrew.misterio.feature_details.DetailsFragment
import andrew.misterio.navigation.Screen
import andrew.misterio.navigation.ScreenMappers
import andrew.misterio.navigation.commands.screens.ToDetails
import andrew.misterio.navigation.commands.screens.ToHome
import andrew.misterio.rickandmorty.R
import android.os.Bundle

class ScreenMappersImpl : ScreenMappers {

    override fun getActionId(screen: Screen): Int = when (screen) {
        is ToDetails -> R.id.navigation_to_details
        is ToHome -> R.id.navigation_to_home
        else -> -1
    }

    override fun getArgsBundle(screen: Screen): Bundle? = when (screen) {
        is ToDetails -> DetailsFragment.createArgs(screen.id)
        else -> null
    }
}
