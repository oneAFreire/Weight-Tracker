package com.oneafreire.weighttracker.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oneafreire.domain.common.Resource
import com.oneafreire.domain.usecase.GetStatisticsScreenDataUseCase
import com.oneafreire.weighttracker.model.StatisticsScreenState
import com.oneafreire.weighttracker.ui.helper.BottomBarItemHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val getStatisticsScreenDataUseCase: GetStatisticsScreenDataUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(StatisticsScreenState())
    val uiState: StateFlow<StatisticsScreenState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getStatisticsScreenDataUseCase.invoke().collect { result ->

                when (result) {
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                diffLastMeasurement = result.data?.diffLastMeasurement,
                                diffLast30Days = result.data?.diffLast30Days,
                                diffCurrentYear = result.data?.diffCurrentYear,
                                diffLastYear = result.data?.diffLastYear,
                                diffFromStart = result.data?.diffFromStart,
                                navItemList = BottomBarItemHelper.parseListToUIItem(
                                    result.data?.navItemList,
                                    appContext.resources
                                )
                            )
                        }
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                navItemList = BottomBarItemHelper.parseListToUIItem(
                                    result.data?.navItemList,
                                    appContext.resources
                                )
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