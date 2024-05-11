package com.oneafreire.weighttracker.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import com.oneafreire.domain.model.WeightMeasurement
import com.oneafreire.weighttracker.R
import com.oneafreire.weighttracker.model.HomeScreenState
import com.oneafreire.weighttracker.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    screenState: HomeScreenState,
    currentScreen: Screen
) {
    Scaffold(
        content = {
            HomeScreenContent(
                modifier = Modifier.padding(it),
                screenState = screenState
            )
        },
        floatingActionButton = { FabAddMeasurement {} },
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
fun HomeScreenContent(modifier: Modifier, screenState: HomeScreenState) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
    ) {
        CurrentData()
        if (screenState.lastMeasurement != null) {
            CurrentMeasurement(data = screenState.lastMeasurement, units = DisplayUnits.KG) // TODO
        }
    }
}

@Composable
fun CurrentData() {
    Text(
        text = "Current data",
        fontWeight = FontWeight.Bold
    )
    Divider(color = Color.LightGray, thickness = 1.dp)
}

@Composable
fun CurrentMeasurement(data: WeightMeasurement, units: DisplayUnits) {

    val displayUnit = when (units) {
        DisplayUnits.KG -> {
            stringResource(id = R.string.display_units_kg)
        }

        DisplayUnits.LBS -> {
            stringResource(id = R.string.display_units_lbs)
        }
    }

    Row(
        modifier = Modifier
            .padding(end = 8.dp, top = 2.dp, bottom = 2.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.last_measurement),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Data",
            fontWeight = FontWeight.Bold
        )
    }
    Divider(color = Color.LightGray, thickness = 1.dp)
    InfoText(
        description = stringResource(id = R.string.weight),
        value = data.weight.toString(),
        units = displayUnit
    )
    InfoText(
        description = stringResource(id = R.string.body_fat),
        value = data.bodyFat.toString(),
        units = stringResource(id = R.string.display_units_percentage)
    )
    InfoText(
        description = stringResource(id = R.string.body_water),
        value = data.bodyWater.toString(),
        units = displayUnit
    )
    InfoText(
        description = stringResource(id = R.string.visceral_fat),
        value = data.visceralFat.toString()
    )
    InfoText(
        description = stringResource(id = R.string.body_mass),
        value = data.bodyMass.toString(),
        units = displayUnit
    )
    InfoText(
        description = stringResource(id = R.string.daily_calories),
        value = data.dailyCalories.toString(),
        units = stringResource(id = R.string.display_units_kcal)
    )
}

@Composable
fun FabAddMeasurement(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() }
    ) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}