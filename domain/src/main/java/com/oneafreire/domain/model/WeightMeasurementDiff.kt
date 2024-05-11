package com.oneafreire.domain.model

import com.oneafreire.domain.common.DisplayUnits

data class WeightMeasurementDiff (
    val weight: Double,
    val bodyFat: Double,
    val bodyWater: Double,
    val visceralFat: Int,
    val bodyMass: Double,
    val units: DisplayUnits
)