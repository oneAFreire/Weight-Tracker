package com.oneafreire.weighttracker.model

import com.oneafreire.domain.model.WeightMeasurementDiff

data class StatisticsScreenState(
    val diffLastMeasurement: WeightMeasurementDiff? = null,
    val diffLast30Days: WeightMeasurementDiff? = null,
    val diffCurrentYear: WeightMeasurementDiff? = null,
    val diffLastYear: WeightMeasurementDiff? = null,
    val diffFromStart: WeightMeasurementDiff? = null,
    val isLoading: Boolean = false,
    val error: String = "",
    val navItemList: List<BottomNavigationItem> = emptyList()
)