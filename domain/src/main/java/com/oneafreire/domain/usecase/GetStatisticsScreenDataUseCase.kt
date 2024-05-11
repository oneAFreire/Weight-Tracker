package com.oneafreire.domain.usecase

import com.oneafreire.domain.common.DisplayUnits
import com.oneafreire.domain.common.Resource
import com.oneafreire.domain.model.StatisticsScreenData
import com.oneafreire.domain.model.WeightMeasurement
import com.oneafreire.domain.model.WeightMeasurementDiff
import com.oneafreire.domain.repository.SettingsRepository
import com.oneafreire.domain.repository.WeightMeasurementsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetStatisticsScreenDataUseCase(
    private val getMenuEntriesUseCase: GetMenuEntriesUseCase,
    private val weightMeasurementsRepository: WeightMeasurementsRepository,
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(): Flow<Resource<StatisticsScreenData>> = flow {

        emit(Resource.Loading(StatisticsScreenData()))

        val menuEntries = getMenuEntriesUseCase.invoke()
        val displayUnits = settingsRepository.current().displayUnits
        val allMeasurements = weightMeasurementsRepository.records()
        val last30DaysMeasurements = weightMeasurementsRepository.last30DaysRecords()
        val currentYearMeasurements = weightMeasurementsRepository.currentYearRecords()
        val lastYearMeasurements = weightMeasurementsRepository.lastYearRecords()

        var diffLastMeasurement: WeightMeasurementDiff? = null
        var diffLast30Days: WeightMeasurementDiff? = null
        var diffCurrentYear: WeightMeasurementDiff? = null
        var diffLastYear: WeightMeasurementDiff? = null
        var diffFromStart: WeightMeasurementDiff? = null

        if (allMeasurements.isNotEmpty() && allMeasurements.size > 1) {

            diffLastMeasurement = calculateDiff(
                measurementA = allMeasurements[allMeasurements.size - 2],
                measurementB = allMeasurements[allMeasurements.size - 1],
                units = displayUnits
            )

            diffFromStart = calculateDiff(
                measurementA = allMeasurements[0],
                measurementB = allMeasurements[allMeasurements.size - 1],
                units = displayUnits
            )
        }

        if (last30DaysMeasurements.isNotEmpty() && last30DaysMeasurements.size > 1) {

            diffLast30Days = calculateDiff(
                measurementA = last30DaysMeasurements[0],
                measurementB = last30DaysMeasurements[last30DaysMeasurements.size - 1],
                units = displayUnits
            )

        }

        if (lastYearMeasurements.isNotEmpty() && lastYearMeasurements.size > 1) {

            diffLastYear = calculateDiff(
                measurementA = lastYearMeasurements[0],
                measurementB = lastYearMeasurements[lastYearMeasurements.size - 1],
                units = displayUnits
            )

        }

        if (currentYearMeasurements.isNotEmpty() && currentYearMeasurements.size > 1) {

            diffCurrentYear = calculateDiff(
                measurementA = currentYearMeasurements[0],
                measurementB = currentYearMeasurements[currentYearMeasurements.size - 1],
                units = displayUnits
            )

        }

        emit(
            Resource.Success(
                StatisticsScreenData(
                    diffLastMeasurement = diffLastMeasurement,
                    diffLast30Days = diffLast30Days,
                    diffCurrentYear = diffCurrentYear,
                    diffLastYear = diffLastYear,
                    diffFromStart = diffFromStart,
                    navItemList = menuEntries
                )
            )
        )
    }

    private fun calculateDiff(
        measurementA: WeightMeasurement?,
        measurementB: WeightMeasurement?,
        units: DisplayUnits
    ): WeightMeasurementDiff? {
        return if (measurementA != null && measurementB != null) {
            WeightMeasurementDiff(
                weight = measurementA.weight - measurementB.weight,
                bodyFat = measurementA.bodyFat - measurementB.bodyFat,
                bodyWater = measurementA.bodyWater - measurementB.bodyWater,
                visceralFat = measurementA.visceralFat - measurementB.visceralFat,
                bodyMass = measurementA.bodyMass - measurementB.bodyMass,
                units = units
            )
        } else {
            null
        }
    }
}