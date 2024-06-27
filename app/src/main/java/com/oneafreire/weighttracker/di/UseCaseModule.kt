package com.oneafreire.weighttracker.di

import com.oneafreire.data.repositoryimpl.MenuEntriesRepositoryImpl
import com.oneafreire.data.repositoryimpl.SettingsRepositoryImpl
import com.oneafreire.data.repositoryimpl.WeightMeasurementsRepositoryImpl
import com.oneafreire.domain.usecase.GetHomeScreenDataUseCase
import com.oneafreire.domain.usecase.GetLastMeasurementUseCase
import com.oneafreire.domain.usecase.GetMenuEntriesUseCase
import com.oneafreire.domain.usecase.GetRecordsScreenDataUseCase
import com.oneafreire.domain.usecase.GetStatisticsScreenDataUseCase
import com.oneafreire.domain.usecase.GetWeightMeasurementsUseCase
import com.oneafreire.domain.usecase.SaveWeightMeasurementUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun providesGetWeightMeasurementsUseCase(
        weightMeasurementsRepositoryImpl: WeightMeasurementsRepositoryImpl
    ): GetWeightMeasurementsUseCase {
        return GetWeightMeasurementsUseCase(weightMeasurementsRepositoryImpl)
    }

    @Provides
    @ViewModelScoped
    fun providesGetMenuEntriesUseCase(menuEntriesRepositoryImpl: MenuEntriesRepositoryImpl): GetMenuEntriesUseCase {
        return GetMenuEntriesUseCase(menuEntriesRepositoryImpl)
    }

    @Provides
    @ViewModelScoped
    fun providesGetLastMeasurementUseCase(
        weightMeasurementsRepositoryImpl: WeightMeasurementsRepositoryImpl
    ): GetLastMeasurementUseCase {
        return GetLastMeasurementUseCase(weightMeasurementsRepositoryImpl)
    }

    @Provides
    @ViewModelScoped
    fun providesGetHomeViewStateUseCase(
        getMenuEntriesUseCase: GetMenuEntriesUseCase,
        getLastMeasurementUseCase: GetLastMeasurementUseCase
    ): GetHomeScreenDataUseCase {
        return GetHomeScreenDataUseCase(
            getMenuEntriesUseCase,
            getLastMeasurementUseCase
        )
    }

    @Provides
    @ViewModelScoped
    fun providesGetRecordsScreenDataUseCase(
        getMenuEntriesUseCase: GetMenuEntriesUseCase,
        getWeightMeasurementsUseCase: GetWeightMeasurementsUseCase
    ): GetRecordsScreenDataUseCase {
        return GetRecordsScreenDataUseCase(
            getMenuEntriesUseCase,
            getWeightMeasurementsUseCase
        )
    }

    @Provides
    @ViewModelScoped
    fun providesGetStatisticsScreenDataUseCase(
        getMenuEntriesUseCase: GetMenuEntriesUseCase,
        weightMeasurementsRepositoryImpl: WeightMeasurementsRepositoryImpl,
        settingsRepositoryImpl: SettingsRepositoryImpl
    ): GetStatisticsScreenDataUseCase {
        return GetStatisticsScreenDataUseCase(
            getMenuEntriesUseCase,
            weightMeasurementsRepositoryImpl,
            settingsRepositoryImpl
        )
    }

    @Provides
    @ViewModelScoped
    fun providesSaveWeightMeasurementUseCase(
        weightMeasurementsRepositoryImpl: WeightMeasurementsRepositoryImpl
    ): SaveWeightMeasurementUseCase {
        return SaveWeightMeasurementUseCase(weightMeasurementsRepositoryImpl)
    }
}