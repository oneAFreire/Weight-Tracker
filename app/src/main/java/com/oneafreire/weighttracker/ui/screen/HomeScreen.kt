package com.oneafreire.weighttracker.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.oneafreire.domain.common.DisplayUnits
import com.oneafreire.domain.model.WeightMeasurement
import com.oneafreire.weighttracker.R
import com.oneafreire.weighttracker.model.HomeScreenState
import com.oneafreire.weighttracker.navigation.Screen
import java.util.Date

@Composable
fun HomeScreen(
    navController: NavHostController,
    screenState: HomeScreenState,
    currentScreen: Screen,
    onAddMeasurement: (WeightMeasurement) -> Unit
) {
    Scaffold(
        content = {
            HomeScreenContent(
                modifier = Modifier.padding(it),
                screenState = screenState
            )
        },
        floatingActionButton = { FabAddMeasurement(onAddMeasurement) },
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
        text = stringResource(id = R.string.current_data),
        fontWeight = FontWeight.Bold
    )
    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
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
            text = stringResource(id = R.string.data),
            fontWeight = FontWeight.Bold
        )
    }
    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
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
fun FabAddMeasurement(onAddMeasurement: (WeightMeasurement) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AddMeasurementDialog(
            onDismiss = { showDialog = false },
            onConfirm = { measurement ->
                onAddMeasurement(measurement)
                showDialog = false
            }
        )
    }

    FloatingActionButton(
        onClick = { showDialog = true }
    ) {
        Icon(Icons.Filled.Add, stringResource(id = R.string.new_measurement))
    }
}

@Composable
fun AddMeasurementDialog(onDismiss: () -> Unit, onConfirm: (WeightMeasurement) -> Unit) {
    val date by remember { mutableStateOf(Date()) }
    var weight by remember { mutableStateOf("") }
    var bodyFat by remember { mutableStateOf("") }
    var bodyWater by remember { mutableStateOf("") }
    var visceralFat by remember { mutableStateOf("") }
    var bodyMass by remember { mutableStateOf("") }
    var dailyCalories by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = stringResource(id = R.string.new_measurement)) },
        text = {
            Column {
                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text(stringResource(id = R.string.weight)) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = bodyFat,
                    onValueChange = { bodyFat = it },
                    label = { Text(stringResource(id = R.string.body_fat)) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = bodyWater,
                    onValueChange = { bodyWater = it },
                    label = { Text(stringResource(id = R.string.body_water)) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = visceralFat,
                    onValueChange = { visceralFat = it },
                    label = { Text(stringResource(id = R.string.visceral_fat)) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = bodyMass,
                    onValueChange = { bodyMass = it },
                    label = { Text(stringResource(id = R.string.body_mass)) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = dailyCalories,
                    onValueChange = { dailyCalories = it },
                    label = { Text(stringResource(id = R.string.daily_calories)) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val newMeasurement = WeightMeasurement(
                    date = date,
                    weight = weight.toDoubleOrNull() ?: 0.0,
                    bodyFat = bodyFat.toDoubleOrNull() ?: 0.0,
                    bodyWater = bodyWater.toDoubleOrNull() ?: 0.0,
                    visceralFat = visceralFat.toIntOrNull() ?: 0,
                    bodyMass = bodyMass.toDoubleOrNull() ?: 0.0,
                    dailyCalories = dailyCalories.toIntOrNull() ?: 0
                )
                onConfirm(newMeasurement)
                onDismiss()
            }) {
                Text(stringResource(id = R.string.add))
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text(stringResource(id = R.string.cancel))
            }
        }
    )
}