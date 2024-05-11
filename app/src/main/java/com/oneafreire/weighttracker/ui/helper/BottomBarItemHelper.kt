package com.oneafreire.weighttracker.ui.helper

import android.content.res.Resources
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import com.oneafreire.domain.common.BottomBarItem
import com.oneafreire.weighttracker.R
import com.oneafreire.weighttracker.model.BottomNavigationItem
import com.oneafreire.weighttracker.navigation.Screen

object BottomBarItemHelper {

    fun parseListToUIItem(
        list: List<BottomBarItem>?,
        resources: Resources
    ): List<BottomNavigationItem> {
        val uiItemList: MutableList<BottomNavigationItem> = mutableListOf()

        if (list == null){
            return uiItemList
        }

        for (entry in list) {
            uiItemList.add(parseEnumToUIItem(entry, resources))
        }

        return uiItemList
    }

    private fun parseEnumToUIItem(item: BottomBarItem, resources: Resources): BottomNavigationItem {
        when (item) {
            BottomBarItem.HOME -> {
                return BottomNavigationItem(
                    title = resources.getString(R.string.home),
                    selectedIcon = Icons.Filled.Home,
                    unselectedIcon = Icons.Outlined.Home,
                    screen = Screen.Home
                )
            }

            BottomBarItem.RECORDS -> {
                return BottomNavigationItem(
                    title = resources.getString(R.string.records),
                    selectedIcon = Icons.Filled.List,
                    unselectedIcon = Icons.Outlined.List,
                    screen = Screen.Records
                )
            }

            BottomBarItem.STATISTICS -> {
                return BottomNavigationItem(
                    title = resources.getString(R.string.statistics),
                    selectedIcon = Icons.Filled.DateRange,
                    unselectedIcon = Icons.Outlined.DateRange,
                    screen = Screen.Statistics
                )
            }
        }
    }
}
