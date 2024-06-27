package com.oneafreire.domain.repository

import com.oneafreire.domain.model.WeightMeasurement
import java.util.Date

interface WeightMeasurementsRepository {
    suspend fun records(): List<WeightMeasurement>
    suspend fun getMeasurementsFromDate(startDate: Date): List<WeightMeasurement>
    suspend fun getMeasurementsBetweenDates(startDate: Date, endDate: Date): List<WeightMeasurement>
    suspend fun lastRecord(): WeightMeasurement?
    suspend fun insert(measurement: WeightMeasurement)
}