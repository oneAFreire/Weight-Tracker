package com.oneafreire.weighttracker.di

import com.oneafreire.data.db.dao.WeightMeasurementDao
import com.oneafreire.data.repositoryimpl.MenuEntriesRepositoryImpl
import com.oneafreire.data.repositoryimpl.SettingsRepositoryImpl
import com.oneafreire.data.repositoryimpl.WeightMeasurementsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWeightMeasurementsRepositoryImpl(
        weightMeasurementDao: WeightMeasurementDao
    ): WeightMeasurementsRepositoryImpl {
        return WeightMeasurementsRepositoryImpl(weightMeasurementDao)
    }

    @Provides
    @Singleton
    fun provideMenuEntriesRepositoryImpl(): MenuEntriesRepositoryImpl {
        return MenuEntriesRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideSettingsRepositoryImpl(): SettingsRepositoryImpl {
        return SettingsRepositoryImpl()
    }
}