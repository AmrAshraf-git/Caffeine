package com.ipro.caffiene.navigation.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ipro.caffiene.navigation.Destination
import com.ipro.caffiene.presentation.screen.CoffeeLoadingScreen

fun NavGraphBuilder.coffeeLoadingScreenRoute(navController: NavHostController){
    composable(Destination.CoffeeLoading.route,
        arguments = listOf(navArgument("coffeeName") {
            type = NavType.StringType
            defaultValue = "Coffee Type" })) {
            navBackStackEntry ->
        val coffeeName = navBackStackEntry.arguments?.getString("coffeeName") ?: "Coffee Type"
        CoffeeLoadingScreen(
            coffeeName = coffeeName,
            onLoadingFinished = { navController.navigate(Destination.CoffeeIsReady.route) }
        )
    }
}