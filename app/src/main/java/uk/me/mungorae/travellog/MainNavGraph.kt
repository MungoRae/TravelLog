package uk.me.mungorae.travellog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.TRAVELS_ROUTE,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Destinations.TRAVELS_ROUTE) {

        }
    }
}