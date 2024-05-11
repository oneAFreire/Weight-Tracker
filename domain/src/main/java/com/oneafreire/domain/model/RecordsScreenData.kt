package com.oneafreire.domain.model

import com.oneafreire.domain.common.BottomBarItem
import com.oneafreire.domain.common.ErrorMessage

data class RecordsScreenData (
    val records: List<WeightMeasurement> = emptyList(),
    val navItemList: List<BottomBarItem> = emptyList()
)