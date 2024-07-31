package com.oneafreire.data.mapper

import com.oneafreire.data.db.entity.WeightMeasurementEntity
import com.oneafreire.domain.model.WeightMeasurement

class WeightMeasurementMapper {
    operator fun invoke(entry: WeightMeasurementEntity?): WeightMeasurement? {
        return entry?.let {
            WeightMeasurement(
                date = it.date,
                weight = it.weight,
                bodyFat = it.bodyFat,
                bodyWater = it.bodyWater,
                visceralFat = it.visceralFat,
                bodyMass = it.bodyMass,
                dailyCalories = it.dailyCalories
            )
        }
    }

    fun parseList(list: List<WeightMeasurementEntity>): List<WeightMeasurement> {
        return list.mapNotNull { invoke(it) }
    }
}