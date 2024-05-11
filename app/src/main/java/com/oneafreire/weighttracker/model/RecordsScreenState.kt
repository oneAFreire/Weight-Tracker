package com.oneafreire.weighttracker.model

import com.oneafreire.domain.model.WeightMeasurement

data class RecordsScreenState (
    val isRefreshing: Boolean = false,
    val error: String = "",
    val records: List<WeightMeasurement> = emptyList(),
    val navItemList: List<BottomNavigationItem> = emptyList()
)