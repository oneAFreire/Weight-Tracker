package com.oneafreire.domain.model

import java.util.Date

data class WeightMeasurement(
    val date: Date,
    val weight: Double,
    val bodyFat: Double,
    val bodyWater: Double,
    val visceralFat: Int,
    val bodyMass: Double,
    val dailyCalories: Int
)
