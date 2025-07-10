package com.ipro.caffiene.navigation.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ipro.caffiene.navigation.Destination
import com.ipro.caffiene.presentation.screen.HomeScreen


fun NavGraphBuilder.homeScreenRoute(navController: NavHostController){
    composable(Destination.Home.route) {
            navBackStackEntry ->
        HomeScreen(
            onBringMyCoffeeClick = { navController.navigate(Destination.CoffeeType.route) }
        )
    }


}