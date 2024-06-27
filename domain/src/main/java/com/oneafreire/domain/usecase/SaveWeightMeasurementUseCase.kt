package com.oneafreire.domain.usecase

import com.oneafreire.domain.model.WeightMeasurement
import com.oneafreire.domain.repository.WeightMeasurementsRepository

class SaveWeightMeasurementUseCase(
    private val weightMeasurementsRepository: WeightMeasurementsRepository
) {
    suspend operator fun invoke(
        measurement: WeightMeasurement
    ) {
        weightMeasurementsRepository.insert(
            measurement
        )
    }
}