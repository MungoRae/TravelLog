package uk.me.mungorae.travellog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.me.mungorae.travellog.addravel.AddTravelScreen
import uk.me.mungorae.travellog.travelslist.TravelsScreen

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.TRAVELS_ROUTE,
    navActions: NavActions = NavActions(navController)
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Destinations.TRAVELS_ROUTE) {
            TravelsScreen(onAddTravel = { navActions.navigateToAddTravel() })
        }
        composable(Destinations.ADD_TRAVEL_ROUTE) {
            AddTravelScreen(onTravelsUpdated = {
                navController.popBackStack()
            })
        }
    }
}