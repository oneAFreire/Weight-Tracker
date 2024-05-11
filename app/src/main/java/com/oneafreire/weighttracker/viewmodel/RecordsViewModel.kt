package com.oneafreire.weighttracker.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oneafreire.domain.common.Resource
import com.oneafreire.domain.usecase.GetRecordsScreenDataUseCase
import com.oneafreire.weighttracker.model.RecordsScreenState
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
class RecordsViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val getRecordsScreenDataUseCase: GetRecordsScreenDataUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecordsScreenState())
    val uiState: StateFlow<RecordsScreenState> = _uiState.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            getRecordsScreenDataUseCase.invoke().collect { result ->

                // TODO
                when (result) {
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                records = result.data?.records ?: emptyList(),
                                navItemList = BottomBarItemHelper.parseListToUIItem(
                                    result.data?.navItemList,
                                    appContext.resources
                                )
                            )
                        }
                    }

                    is Resource.Error -> {

                    }

                    is Resource.Loading -> {

                    }
                }
            }
        }
    }
}