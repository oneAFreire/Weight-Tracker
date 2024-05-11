package com.oneafreire.weighttracker.navigation

sealed class  Screen(val route: String) {
    data object Home : Screen("home")
    data object Statistics : Screen("statistics")
    data object Records : Screen("records")
}
