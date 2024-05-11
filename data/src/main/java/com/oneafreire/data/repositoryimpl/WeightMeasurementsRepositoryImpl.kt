package com.oneafreire.data.repositoryimpl

import com.oneafreire.domain.model.WeightMeasurement
import com.oneafreire.domain.repository.WeightMeasurementsRepository
import java.util.Date

class WeightMeasurementsRepositoryImpl : WeightMeasurementsRepository {
    override suspend fun records(): List<WeightMeasurement> {
        return listOf(
            WeightMeasurement(
                date = Date(),
                weight = 99.1,
                bodyFat = 30.2,
                bodyWater = 49.2,
                visceralFat = 30,
                bodyMass = 40.0,
                dailyCalories = 2041
            ),
            WeightMeasurement(
                date = Date(),
                weight = 89.1,
                bodyFat = 37.2,
                bodyWater = 39.2,
                visceralFat = 30,
                bodyMass = 40.0,
                dailyCalories = 2041
            ),
            WeightMeasurement(
                date = Date(),
                weight = 79.1,
                bodyFat = 20.2,
                bodyWater = 59.2,
                visceralFat = 30,
                bodyMass = 40.0,
                dailyCalories = 2041
            )
        )
    }

    override suspend fun last30DaysRecords(): List<WeightMeasurement>{
        return listOf(
            WeightMeasurement(
                date = Date(),
                weight = 99.1,
                bodyFat = 30.2,
                bodyWater = 49.2,
                visceralFat = 30,
                bodyMass = 40.0,
                dailyCalories = 2041
            ),
            WeightMeasurement(
                date = Date(),
                weight = 89.1,
                bodyFat = 37.2,
                bodyWater = 39.2,
                visceralFat = 30,
                bodyMass = 40.0,
                dailyCalories = 2041
            ),
            WeightMeasurement(
                date = Date(),
                weight = 79.1,
                bodyFat = 20.2,
                bodyWater = 59.2,
                visceralFat = 30,
                bodyMass = 40.0,
                dailyCalories = 2041
            )
        )
    }

    override suspend fun currentYearRecords(): List<WeightMeasurement>{
        return listOf(
            WeightMeasurement(
                date = Date(),
                weight = 99.1,
                bodyFat = 30.2,
                bodyWater = 49.2,
                visceralFat = 30,
                bodyMass = 40.0,
                dailyCalories = 2041
            ),
            WeightMeasurement(
                date = Date(),
                weight = 89.1,
                bodyFat = 37.2,
                bodyWater = 39.2,
                visceralFat = 30,
                bodyMass = 40.0,
                dailyCalories = 2041
            ),
            WeightMeasurement(
                date = Date(),
                weight = 79.1,
                bodyFat = 20.2,
                bodyWater = 59.2,
                visceralFat = 30,
                bodyMass = 40.0,
                dailyCalories = 2041
            )
        )
    }

    override suspend fun lastYearRecords(): List<WeightMeasurement>{
        return listOf(
            WeightMeasurement(
                date = Date(),
                weight = 99.1,
                bodyFat = 30.2,
                bodyWater = 49.2,
                visceralFat = 30,
                bodyMass = 40.0,
                dailyCalories = 2041
            ),
            WeightMeasurement(
                date = Date(),
                weight = 89.1,
                bodyFat = 37.2,
                bodyWater = 39.2,
                visceralFat = 30,
                bodyMass = 40.0,
                dailyCalories = 2041
            ),
            WeightMeasurement(
                date = Date(),
                weight = 79.1,
                bodyFat = 20.2,
                bodyWater = 59.2,
                visceralFat = 30,
                bodyMass = 40.0,
                dailyCalories = 2041
            )
        )
    }

    override suspend fun lastRecord(): WeightMeasurement? {
        return WeightMeasurement(
            date = Date(),
            weight = 79.1,
            bodyFat = 20.2,
            bodyWater = 59.2,
            visceralFat = 30,
            bodyMass = 40.0,
            dailyCalories = 2041
        )
    }
}