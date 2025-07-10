package com.ipro.caffiene.navigation

sealed class Destination(val route: String) {
    object Home : Destination("home")
    object CoffeeType : Destination("coffee_type")
    object CoffeeSize : Destination("coffee_size")
    object CoffeeLoading : Destination("coffee_loading")
    object CoffeeIsReady : Destination("coffee_is_ready")
}