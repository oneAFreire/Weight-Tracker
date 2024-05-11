package com.oneafreire.weighttracker.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.oneafreire.domain.common.DisplayUnits
import com.oneafreire.domain.model.WeightMeasurementDiff
import com.oneafreire.weighttracker.R
import com.oneafreire.weighttracker.model.StatisticsScreenState
import com.oneafreire.weighttracker.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsScreen(
    navController: NavHostController,
    screenState: StatisticsScreenState,
    currentScreen: Screen
) {
    Scaffold(
        content = {
            StatisticsScreenContent(
                screenState = screenState,
                modifier = Modifier.padding(it)
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                navItemList = screenState.navItemList,
                currentScreen = currentScreen
            )
        }
    )
}

@Composable
fun StatisticsScreenContent(
    screenState: StatisticsScreenState,
    modifier: Modifier
) {
    Column(
        modifier = modifier.padding(all = 8.dp)
    ) {
        if (screenState.diffLastMeasurement != null) {
            DiffInfoUI(
                stringResource(id = R.string.diff_last_measurements),
                screenState.diffLastMeasurement
            )
        }

        if (screenState.diffLast30Days != null) {
            DiffInfoUI(stringResource(id = R.string.diff_last_30_days), screenState.diffLast30Days)
        }

        if (screenState.diffCurrentYear != null) {
            DiffInfoUI(stringResource(id = R.string.diff_current_year), screenState.diffCurrentYear)
        }

        if (screenState.diffLastYear != null) {
            DiffInfoUI(stringResource(id = R.string.diff_last_year), screenState.diffLastYear)
        }

        if (screenState.diffFromStart != null) {
            DiffInfoUI(stringResource(id = R.string.diff_last_entry), screenState.diffFromStart)
        }
    }
}

@Composable
fun DiffInfoUI(title: String, data: WeightMeasurementDiff) {

    val displayUnit = when (data.units) {
        DisplayUnits.KG -> {
            stringResource(id = R.string.display_units_kg)
        }

        DisplayUnits.LBS -> {
            stringResource(id = R.string.display_units_lbs)
        }
    }

    Column {
        Text(
            text = title,
            fontWeight = FontWeight.Bold
        )
        Divider(color = Color.LightGray, thickness = 1.dp)
        InfoText(
            description = stringResource(id = R.string.weight),
            value = data.weight.toString(),
            units = displayUnit,
            weightGain = data.weight > 0
        )
        InfoText(
            description = stringResource(id = R.string.body_fat),
            value = data.bodyFat.toString(),
            units = stringResource(id = R.string.display_units_percentage),
            weightGain = data.bodyFat > 0
        )
        InfoText(
            description = stringResource(id = R.string.body_water),
            value = data.bodyWater.toString(),
            units = displayUnit,
            weightGain = data.bodyWater > 0
        )
        InfoText(
            description = stringResource(id = R.string.visceral_fat),
            value = data.visceralFat.toString(),
            weightGain = data.visceralFat < 0
        )
        InfoText(
            description = stringResource(id = R.string.body_mass),
            value = data.bodyMass.toString(),
            units = displayUnit,
            weightGain = data.bodyMass > 0
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}