package com.oneafreire.weighttracker.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.oneafreire.weighttracker.navigation.Screen

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val screen: Screen
)