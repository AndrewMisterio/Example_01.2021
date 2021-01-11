package andrew.misterio.navigation.commands

import andrew.misterio.navigation.Command
import andrew.misterio.navigation.Screen
import androidx.navigation.Navigator

data class Forward(
    val screen: Screen,
    // TODO check to leak view refs
    val extras: Navigator.Extras? = null
) : Command
