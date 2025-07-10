package com.ipro.caffiene.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ipro.caffiene.navigation.route.coffeeIsReadyScreenRoute
import com.ipro.caffiene.navigation.route.coffeeLoadingScreenRoute
import com.ipro.caffiene.navigation.route.coffeeSizeScreenRoute
import com.ipro.caffiene.navigation.route.coffeeTypeScreenRoute
import com.ipro.caffiene.navigation.route.homeScreenRoute



@Composable
fun CaffeineNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Home.route,
        enterTransition = {
            fadeIn(animationSpec = tween(700)) + // 0.7 second fade
                    slideInHorizontally(
                        animationSpec = tween(700),
                        initialOffsetX = { width -> width }
                    )
        },
        exitTransition = {
            fadeOut(animationSpec = tween(700)) +
                    slideOutHorizontally(
                        animationSpec = tween(700),
                        targetOffsetX = { width -> -width / 3 }
                    )
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(700)) +
                    slideInHorizontally(
                        animationSpec = tween(700),
                        initialOffsetX = { width -> -width / 3 }
                    )
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(700)) +
                    slideOutHorizontally(
                        animationSpec = tween(700),
                        targetOffsetX = { width -> width }
                    )
        }
    ) {
        homeScreenRoute(navController)
        coffeeTypeScreenRoute(navController)
        coffeeSizeScreenRoute(navController)
        coffeeLoadingScreenRoute(navController)
        coffeeIsReadyScreenRoute(navController)
    }
}