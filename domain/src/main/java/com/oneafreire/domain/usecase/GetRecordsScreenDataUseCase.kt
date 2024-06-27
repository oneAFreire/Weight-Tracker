package com.oneafreire.domain.usecase

import com.oneafreire.domain.common.ErrorMessage
import com.oneafreire.domain.common.Resource
import com.oneafreire.domain.model.RecordsScreenData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecordsScreenDataUseCase(
    private val getMenuEntriesUseCase: GetMenuEntriesUseCase,
    private val getWeightMeasurementsUseCase: GetWeightMeasurementsUseCase
) {
    operator fun invoke(): Flow<Resource<RecordsScreenData>> = flow {

        emit(Resource.Loading(RecordsScreenData()))

        val menuEntries = getMenuEntriesUseCase.invoke()
        val records = getWeightMeasurementsUseCase.invoke()

        if (menuEntries.isEmpty()) {
            emit(
                Resource.Error(
                    ErrorMessage.EMPTY_MENU,
                    RecordsScreenData(
                        navItemList = menuEntries
                    )
                )
            )
        } else if (records.isEmpty()) {
            emit(
                Resource.Error(
                    ErrorMessage.EMPTY_DATA,
                    RecordsScreenData(
                        navItemList = menuEntries
                    )
                )
            )
        } else {
            emit(
                Resource.Success(
                    RecordsScreenData(
                        records = records,
                        navItemList = menuEntries
                    )
                )
            )
        }
    }
}