package com.ipro.caffiene.navigation.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ipro.caffiene.navigation.Destination
import com.ipro.caffiene.presentation.screen.CoffeeTypeScreen

fun NavGraphBuilder.coffeeTypeScreenRoute(navController: NavHostController){
    composable(Destination.CoffeeType.route) {
            navBackStackEntry ->
        CoffeeTypeScreen(
            onContinueClick = { selectedCoffeeName ->
                navController.navigate(Destination.CoffeeSize.createRoute(selectedCoffeeName)) }
        )
    }
}