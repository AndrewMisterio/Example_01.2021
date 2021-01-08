package andrew.misterio.navigation

import androidx.navigation.NavController

interface NavControllerOwner {
    val navController: NavController?
    fun closeActivity()
}
