package com.oneafreire.weighttracker.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.oneafreire.weighttracker.model.BottomNavigationItem
import com.oneafreire.weighttracker.navigation.Screen

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    navItemList: List<BottomNavigationItem>,
    currentScreen: Screen
) {
    NavigationBar {
        navItemList.forEach { item ->
            NavigationBarItem(
                selected = currentScreen == item.screen,
                onClick = {
                    navController.navigate(item.screen.route) {
                        /*popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }*/
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = {
                    Text(text = item.title)
                },
                icon = {
                    Box {
                        Icon(
                            imageVector = if (currentScreen == item.screen) {
                                item.selectedIcon
                            } else item.unselectedIcon,
                            contentDescription = item.title
                        )
                    }
                }
            )
        }
    }
}