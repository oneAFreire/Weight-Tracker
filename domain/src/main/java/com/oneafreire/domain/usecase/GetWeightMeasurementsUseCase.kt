package com.oneafreire.domain.usecase

import com.oneafreire.domain.model.WeightMeasurement
import com.oneafreire.domain.repository.WeightMeasurementsRepository

class GetWeightMeasurementsUseCase(
    private val weightMeasurementsRepository: WeightMeasurementsRepository
) {
    suspend operator fun invoke(): List<WeightMeasurement> {
        return weightMeasurementsRepository.records()
    }
}