package com.oneafreire.domain.usecase

import com.oneafreire.domain.common.ErrorMessage
import com.oneafreire.domain.common.Resource
import com.oneafreire.domain.model.HomeScreenData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHomeScreenDataUseCase(
    private val getMenuEntriesUseCase: GetMenuEntriesUseCase,
    private val getLastMeasurementUseCase: GetLastMeasurementUseCase
) {
    operator fun invoke(): Flow<Resource<HomeScreenData>> = flow {

        emit(Resource.Loading(HomeScreenData()))

        val menuEntries = getMenuEntriesUseCase.invoke()
        val lastMeasurement = getLastMeasurementUseCase.invoke()

        if (menuEntries.isEmpty()) {
            emit(
                Resource.Error(
                    ErrorMessage.EMPTY_MENU
                )
            )
        } else if (lastMeasurement == null) {
            emit(
                Resource.Error(
                    ErrorMessage.EMPTY_DATA
                )
            )
        } else {
            emit(
                Resource.Success(
                    HomeScreenData(
                        navItemList = menuEntries,
                        lastMeasurement = lastMeasurement
                    )
                )
            )
        }
    }
}