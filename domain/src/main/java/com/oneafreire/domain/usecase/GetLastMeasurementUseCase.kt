package com.oneafreire.domain.usecase

import com.oneafreire.domain.model.WeightMeasurement
import com.oneafreire.domain.repository.WeightMeasurementsRepository

class GetLastMeasurementUseCase(
    private val weightMeasurementsRepository: WeightMeasurementsRepository
) {
    suspend operator fun invoke(): WeightMeasurement? {
        return weightMeasurementsRepository.lastRecord()
    }
}