package andrew.misterio.feature_base.navigation.screens

import andrew.misterio.feature_base.R
import andrew.misterio.navigation.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
object ToHome : Screen {
    override val actionId: Int
        get() = R.id.navigation_to_home
}
