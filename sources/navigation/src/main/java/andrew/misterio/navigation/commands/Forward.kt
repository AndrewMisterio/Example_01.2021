package andrew.misterio.navigation.commands

import andrew.misterio.navigation.Command
import andrew.misterio.navigation.Screen
import androidx.navigation.Navigator

data class Forward(
    val screen: Screen,
    val extras: Navigator.Extras? = null
): Command
