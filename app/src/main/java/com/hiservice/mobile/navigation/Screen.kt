package com.services.finalsubmissionjetpackcompose.ui.navigation

sealed class Screen(val route: String) {
    object OnBoard : Screen("on-board")
    object Login : Screen("login")
    object Register : Screen("register")
    object Dashboard : Screen("dashboard")
}