package com.oneafreire.domain.model

import com.oneafreire.domain.common.BottomBarItem
import com.oneafreire.domain.common.ErrorMessage

data class StatisticsScreenData(
    val diffLastMeasurement: WeightMeasurementDiff? = null,
    val diffLast30Days: WeightMeasurementDiff? = null,
    val diffCurrentYear: WeightMeasurementDiff? = null,
    val diffLastYear: WeightMeasurementDiff? = null,
    val diffFromStart: WeightMeasurementDiff? = null,
    val error: ErrorMessage? = null,
    val navItemList: List<BottomBarItem> = emptyList()
)