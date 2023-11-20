package com.services.finalsubmissionjetpackcompose.ui.navigation

sealed class Screen(val route: String) {
    object OnBoard : Screen("on-board")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
}