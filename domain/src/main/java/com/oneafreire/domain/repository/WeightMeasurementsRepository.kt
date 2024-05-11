package com.oneafreire.domain.repository

import com.oneafreire.domain.model.WeightMeasurement

interface WeightMeasurementsRepository {
    suspend fun records(): List<WeightMeasurement>
    suspend fun last30DaysRecords(): List<WeightMeasurement>
    suspend fun currentYearRecords(): List<WeightMeasurement>
    suspend fun lastYearRecords(): List<WeightMeasurement>
    suspend fun lastRecord(): WeightMeasurement?
}