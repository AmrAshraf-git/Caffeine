package com.ipro.caffiene.navigation

sealed class Destination(val route: String) {
    object Home : Destination("home")
    object CoffeeType : Destination("coffee_type")
    object CoffeeSize : Destination("coffee_size/{coffeeName}"){
        fun createRoute(coffeeName: String) = "coffee_size/$coffeeName"
    }
    object CoffeeLoading : Destination("coffee_loading/{coffeeName}"){
        fun createRoute(coffeeName: String) = "coffee_loading/$coffeeName"
    }
    object CoffeeIsReady : Destination("coffee_is_ready")
}