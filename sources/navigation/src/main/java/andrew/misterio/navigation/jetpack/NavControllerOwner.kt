package andrew.misterio.navigation.jetpack

import androidx.navigation.NavController

interface NavControllerOwner {
    val navController: NavController?
    fun closeActivity()
}
