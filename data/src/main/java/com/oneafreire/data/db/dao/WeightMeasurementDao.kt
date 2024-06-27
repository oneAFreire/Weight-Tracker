package com.oneafreire.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.oneafreire.data.db.DatabaseDefinition.TABLE_MEASUREMENTS
import com.oneafreire.data.db.entity.WeightMeasurementEntity
import java.util.Date

@Dao
interface WeightMeasurementDao {
    @Insert
    fun insert(weightMeasurement: WeightMeasurementEntity)

    @Query("SELECT * FROM $TABLE_MEASUREMENTS")
    fun getAllMeasurements(): List<WeightMeasurementEntity>

    @Query("SELECT * FROM $TABLE_MEASUREMENTS WHERE date >= :startDate")
    fun getMeasurementsFromDate(startDate: Date): List<WeightMeasurementEntity>

    @Query("SELECT * FROM $TABLE_MEASUREMENTS WHERE date BETWEEN :startOfLastYear AND :endOfLastYear")
    fun getMeasurementsBetweenDates(startOfLastYear: Date, endOfLastYear: Date): List<WeightMeasurementEntity>

    @Query("SELECT * FROM $TABLE_MEASUREMENTS ORDER BY date DESC LIMIT 1")
    fun getMostRecentMeasurement(): WeightMeasurementEntity?
}