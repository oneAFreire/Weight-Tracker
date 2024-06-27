package com.oneafreire.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.oneafreire.data.db.dao.WeightMeasurementDao
import com.oneafreire.data.db.entity.WeightMeasurementEntity

@Database(entities = [WeightMeasurementEntity::class], exportSchema = false, version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun weightMeasurementDao(): WeightMeasurementDao
}