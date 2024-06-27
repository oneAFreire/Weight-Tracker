package com.oneafreire.data.repositoryimpl

import com.oneafreire.data.db.dao.WeightMeasurementDao
import com.oneafreire.data.db.entity.WeightMeasurementEntity
import com.oneafreire.data.mapper.WeightMeasurementMapper
import com.oneafreire.domain.model.WeightMeasurement
import com.oneafreire.domain.repository.WeightMeasurementsRepository
import java.util.Date

class WeightMeasurementsRepositoryImpl(
    private val weightMeasurementDao: WeightMeasurementDao
) : WeightMeasurementsRepository {
    override suspend fun records(): List<WeightMeasurement> {
        return WeightMeasurementMapper().parseList(weightMeasurementDao.getAllMeasurements())
    }

    override suspend fun getMeasurementsFromDate(startDate: Date): List<WeightMeasurement> {
        return WeightMeasurementMapper().parseList(weightMeasurementDao.getMeasurementsFromDate(startDate))
    }

    override suspend fun getMeasurementsBetweenDates(startDate: Date, endDate: Date): List<WeightMeasurement> {
        return WeightMeasurementMapper().parseList(weightMeasurementDao.getMeasurementsBetweenDates(startDate, endDate))
    }

    override suspend fun lastRecord(): WeightMeasurement? {
        return WeightMeasurementMapper().invoke(weightMeasurementDao.getMostRecentMeasurement())
    }

    override suspend fun insert(measurement: WeightMeasurement) {
        weightMeasurementDao.insert(
            WeightMeasurementEntity(
                date = measurement.date,
                weight = measurement.weight,
                bodyFat = measurement.bodyFat,
                bodyWater = measurement.bodyWater,
                visceralFat = measurement.visceralFat,
                bodyMass = measurement.bodyMass,
                dailyCalories = measurement.dailyCalories
            )
        )
    }
}