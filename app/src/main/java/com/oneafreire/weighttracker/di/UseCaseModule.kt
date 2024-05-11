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
    fun providesGetWeightMeasurementsUseCase(): GetWeightMeasurementsUseCase {
        return GetWeightMeasurementsUseCase(WeightMeasurementsRepositoryImpl())
    }

    @Provides
    @ViewModelScoped
    fun providesGetMenuEntriesUseCase(): GetMenuEntriesUseCase {
        return GetMenuEntriesUseCase(MenuEntriesRepositoryImpl())
    }

    @Provides
    @ViewModelScoped
    fun providesGetHomeViewStateUseCase(): GetHomeScreenDataUseCase {
        return GetHomeScreenDataUseCase(
            providesGetMenuEntriesUseCase(),
            providesGetLastMeasurementUseCase()
        )
    }

    @Provides
    @ViewModelScoped
    fun providesGetRecordsScreenDataUseCase(): GetRecordsScreenDataUseCase {
        return GetRecordsScreenDataUseCase(
            providesGetMenuEntriesUseCase(),
            providesGetWeightMeasurementsUseCase()
        )
    }

    @Provides
    @ViewModelScoped
    fun providesGetStatisticsScreenDataUseCase(): GetStatisticsScreenDataUseCase {
        return GetStatisticsScreenDataUseCase(
            providesGetMenuEntriesUseCase(),
            WeightMeasurementsRepositoryImpl(),
            SettingsRepositoryImpl()
        )
    }

    @Provides
    @ViewModelScoped
    fun providesGetLastMeasurementUseCase(): GetLastMeasurementUseCase {
        return GetLastMeasurementUseCase(WeightMeasurementsRepositoryImpl())
    }
}