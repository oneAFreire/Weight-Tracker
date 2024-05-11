package com.oneafreire.weighttracker.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.oneafreire.domain.model.WeightMeasurement
import com.oneafreire.weighttracker.R
import com.oneafreire.weighttracker.model.RecordsScreenState
import com.oneafreire.weighttracker.navigation.Screen
import com.oneafreire.weighttracker.ui.theme.surface
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordsScreen(
    navController: NavHostController,
    screenState: RecordsScreenState,
    onRefreshData: () -> Unit,
    currentScreen: Screen
) {
    Scaffold(
        content = {
            RecordsScreenContent(
                screenState = screenState,
                modifier = Modifier.padding(it),
                onRefreshData = { onRefreshData() }
            )
        },
        // TODO
        // Create export data feature
       // floatingActionButton = { FabExportData {} },
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
fun FabExportData(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() }
    ) {
        Icon(Icons.Filled.ExitToApp, "Floating action button.")
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecordsScreenContent(
    screenState: RecordsScreenState,
    onRefreshData: () -> Unit,
    modifier: Modifier
) {
    SwipeRefresh(
        state = SwipeRefreshState(screenState.isRefreshing),
        modifier = modifier,
        onRefresh = { onRefreshData() }
    )
    {
        LazyColumn {
            stickyHeader {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.LightGray)
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.date),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(0.2f)
                    )
                    Text(
                        text = stringResource(id = R.string.weight),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(0.2f)
                    )
                    Text(
                        text = stringResource(id = R.string.body_fat),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(0.2f)
                    )
                    Text(
                        text = stringResource(id = R.string.body_water),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(0.2f)
                    )
                    Text(
                        text = stringResource(id = R.string.visceral_fat),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(0.2f)
                    )
                    Text(
                        text = stringResource(id = R.string.body_mass),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(0.2f)
                    )
                }
            }

            itemsIndexed(screenState.records) { index, item ->
                MeasurementItem(
                    item = item,
                    isOddRow = index % 2 == 0
                )
            }
        }
    }
}

@Composable
fun MeasurementItem(item: WeightMeasurement, isOddRow: Boolean) {

    val backgroundColor = if (isOddRow) surface else Color.White

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween
    ) {
        Text(
            text =
            SimpleDateFormat(
                "yyyy/MM/dd",
                Locale.getDefault()
            ).format(item.date),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        Text(
            text = "${item.weight} kg",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        Text(
            text = "${item.bodyFat} %",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        Text(
            text = "${item.bodyWater} kg",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        Text(
            text = item.visceralFat.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
        Text(
            text = "${item.bodyMass} kg",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.2f)
        )
    }
}