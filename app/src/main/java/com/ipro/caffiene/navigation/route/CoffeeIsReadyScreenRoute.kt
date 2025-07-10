package com.ipro.caffiene.navigation.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ipro.caffiene.navigation.Destination
import com.ipro.caffiene.presentation.screen.CoffeeIsReadyScreen

fun NavGraphBuilder.coffeeIsReadyScreenRoute(navController: NavHostController){
    composable(Destination.CoffeeIsReady.route) {
            navBackStackEntry ->
        CoffeeIsReadyScreen(

        )
    }
}