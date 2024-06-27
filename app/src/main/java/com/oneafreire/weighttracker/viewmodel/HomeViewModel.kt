package com.oneafreire.weighttracker.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oneafreire.domain.common.Resource
import com.oneafreire.domain.model.WeightMeasurement
import com.oneafreire.weighttracker.ui.helper.BottomBarItemHelper
import com.oneafreire.domain.usecase.GetHomeScreenDataUseCase
import com.oneafreire.domain.usecase.SaveWeightMeasurementUseCase
import com.oneafreire.weighttracker.model.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val getHomeScreenDataUseCase: GetHomeScreenDataUseCase,
    private val saveWeightMeasurementUseCase: SaveWeightMeasurementUseCase
) : ViewModel() {
    fun insertMeasurement(measurement: WeightMeasurement) {
        viewModelScope.launch {
            saveWeightMeasurementUseCase.invoke(measurement)
        }
    }

    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState: StateFlow<HomeScreenState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getHomeScreenDataUseCase.invoke().collect{ result ->

                when (result) {
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                navItemList = BottomBarItemHelper.parseListToUIItem(result.data?.navItemList, appContext.resources),
                                lastMeasurement = result.data?.lastMeasurement
                            )
                        }
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                navItemList = BottomBarItemHelper.parseListToUIItem(result.data?.navItemList, appContext.resources)
                            )
                        }

                    }

                    is Resource.Loading -> {
                        _uiState.update {
                            it.copy(
                                navItemList = BottomBarItemHelper.parseListToUIItem(
                                    result.data?.navItemList,
                                    appContext.resources
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}