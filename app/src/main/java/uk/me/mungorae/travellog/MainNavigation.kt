package uk.me.mungorae.travellog

import androidx.navigation.NavHostController
import uk.me.mungorae.travellog.Screens.ADD_TRAVEL_SCREEN
import uk.me.mungorae.travellog.Screens.TRAVELS_SCREEN

private object Screens {
    const val TRAVELS_SCREEN = "travels"
    const val ADD_TRAVEL_SCREEN = "add_travel"
}

object Destinations {
    const val TRAVELS_ROUTE = TRAVELS_SCREEN
    const val ADD_TRAVEL_ROUTE = ADD_TRAVEL_SCREEN
}

class NavActions(private val navController: NavHostController) {

    fun navigateToAddTravel() {
        navController.navigate(ADD_TRAVEL_SCREEN)
    }
}
