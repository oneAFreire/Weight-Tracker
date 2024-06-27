package com.oneafreire.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oneafreire.data.db.DatabaseDefinition.TABLE_MEASUREMENTS
import java.util.Date

@Entity(tableName = TABLE_MEASUREMENTS)
data class WeightMeasurementEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: Date,
    val weight: Double,
    val bodyFat: Double,
    val bodyWater: Double,
    val visceralFat: Int,
    val bodyMass: Double,
    val dailyCalories: Int
)