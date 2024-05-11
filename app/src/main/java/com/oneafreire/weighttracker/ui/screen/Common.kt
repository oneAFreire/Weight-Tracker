package com.oneafreire.weighttracker.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun InfoText(description: String, value: String, units: String? = null, weightGain: Boolean? = null) {
    Row(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 2.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = description
        )
        Spacer(modifier = Modifier.width(8.dp))
        Row {
            if (weightGain == null) {
                Text(
                    text = value
                )
            } else {
                Text(
                    text = value,
                    color = if (weightGain) {
                        Color.Red
                    } else {
                        Color.Green
                    }
                )
            }
            if (units != null) {
                Text(
                    text = " $units"
                )
            }
        }
    }
}