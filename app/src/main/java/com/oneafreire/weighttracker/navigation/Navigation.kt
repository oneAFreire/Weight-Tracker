package com.oneafreire.weighttracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oneafreire.weighttracker.ui.screen.HomeScreen
import com.oneafreire.weighttracker.ui.screen.RecordsScreen
import com.oneafreire.weighttracker.ui.screen.StatisticsScreen
import com.oneafreire.weighttracker.viewmodel.HomeViewModel
import com.oneafreire.weighttracker.viewmodel.RecordsViewModel
import com.oneafreire.weighttracker.viewmodel.StatisticsViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            val uiState by homeViewModel.uiState.collectAsState()

            HomeScreen(
                navController = navController,
                screenState = uiState,
                currentScreen = Screen.Home
            )
        }

        composable(route = Screen.Statistics.route) {
            val statisticsViewModel: StatisticsViewModel = hiltViewModel()
            val uiState by statisticsViewModel.uiState.collectAsState()

            StatisticsScreen(
                navController = navController,
                screenState = uiState,
                currentScreen = Screen.Statistics
            )
        }

        composable(route = Screen.Records.route) {
            val recordsViewModel: RecordsViewModel = hiltViewModel()
            val uiState by recordsViewModel.uiState.collectAsState()

            RecordsScreen(
                navController = navController,
                screenState = uiState,
                onRefreshData = { recordsViewModel.refresh() },
                currentScreen = Screen.Records
            )
        }
    }
}