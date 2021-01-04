package andrew.misterio.rickandmorty.navigation

import andrew.misterio.feature_base.navigation.Navigator
import andrew.misterio.rickandmorty.R
import androidx.navigation.NavController

class NavigatorImpl(private val controller: NavController) : Navigator {

    override fun toHome() = controller.navigate(R.id.action_mainFragment_to_homeFragment)
}
