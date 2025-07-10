package com.ipro.caffiene.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ipro.caffiene.presentation.screen.CoffeeIsReadyScreen
import com.ipro.caffiene.presentation.screen.CoffeeLoadingScreen
import com.ipro.caffiene.presentation.screen.CoffeeSizeScreen
import com.ipro.caffiene.presentation.screen.CoffeeTypeScreen
import com.ipro.caffiene.presentation.screen.HomeScreen


@Composable
fun CaffeineNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Home.route
    ) {
        composable(Destination.Home.route) {
            HomeScreen(
                onBringMyCoffeeClick = { navController.navigate(Destination.CoffeeType.route) }
            )
        }
        composable(Destination.CoffeeType.route) {
            CoffeeTypeScreen(
                onContinueClick = { navController.navigate(Destination.CoffeeSize.route) }
            )
        }
        composable(Destination.CoffeeSize.route) {
            CoffeeSizeScreen(
                onContinueClick = { navController.navigate(Destination.CoffeeLoading.route) }
            )
        }
        composable(Destination.CoffeeLoading.route) {
            CoffeeLoadingScreen(
                onLoadingFinished = { navController.navigate(Destination.CoffeeIsReady.route) }
            )
        }
        composable(Destination.CoffeeIsReady.route) {
            CoffeeIsReadyScreen(

            )
        }
    }
}