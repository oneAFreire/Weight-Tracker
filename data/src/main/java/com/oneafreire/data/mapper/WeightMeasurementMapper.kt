package com.oneafreire.data.mapper

import com.oneafreire.data.db.entity.WeightMeasurementEntity
import com.oneafreire.domain.model.WeightMeasurement

class WeightMeasurementMapper {
    operator fun invoke(entry: WeightMeasurementEntity?): WeightMeasurement? {
        return if (entry == null) {
            null
        } else {
            WeightMeasurement(
                date = entry.date,
                weight = entry.weight,
                bodyFat = entry.bodyFat,
                bodyWater = entry.bodyWater,
                visceralFat = entry.visceralFat,
                bodyMass = entry.bodyMass,
                dailyCalories = entry.dailyCalories
            )
        }
    }

    fun parseList(list: List<WeightMeasurementEntity>): List<WeightMeasurement> {
        val returnList: MutableList<WeightMeasurement> = mutableListOf()

        for (entry in list) {
            invoke(entry)?.let { returnList.add(it) }
        }

        return returnList
    }
}