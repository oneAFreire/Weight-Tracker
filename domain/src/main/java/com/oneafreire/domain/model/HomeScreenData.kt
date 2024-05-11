package com.oneafreire.domain.model

import com.oneafreire.domain.common.BottomBarItem
import com.oneafreire.domain.common.ErrorMessage

data class HomeScreenData(
    val navItemList: List<BottomBarItem> = emptyList(),
    val lastMeasurement: WeightMeasurement? = null
)