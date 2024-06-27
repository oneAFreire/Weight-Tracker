package com.oneafreire.data.di

import android.content.Context
import androidx.room.Room
import com.oneafreire.data.db.AppDatabase
import com.oneafreire.data.db.DatabaseDefinition
import com.oneafreire.data.db.dao.WeightMeasurementDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DatabaseDefinition.NAME
        ).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideWeightMeasurementDao(db: AppDatabase): WeightMeasurementDao {
        return db.weightMeasurementDao()
    }
}