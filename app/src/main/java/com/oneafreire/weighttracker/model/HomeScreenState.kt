package com.oneafreire.weighttracker.model

import com.oneafreire.domain.model.WeightMeasurement

data class HomeScreenState(
    val isLoading: Boolean = false,
    val error: String = "",
    val navItemList: List<BottomNavigationItem> = emptyList(),
    val lastMeasurement: WeightMeasurement? = null
)