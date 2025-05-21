package com.rylov.weatherforecast.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InputSection(
    cityName: String,
    days: Int,
    onCityNameChange: (String) -> Unit,
    onDaysChange: (Int) -> Unit,
    onAddCity: (String) -> Unit,
    onFetchWeather: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = cityName,
            onValueChange = onCityNameChange,
            label = { Text("City name") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Forecast days:")
            Slider(
                value = days.toFloat(),
                onValueChange = { onDaysChange(it.toInt()) },
                valueRange = 1f..16f,
                steps = 15,
                modifier = Modifier.weight(1f)
            )
            Text("$days")
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { onAddCity(cityName) },
                enabled = cityName.isNotBlank(),
                modifier = Modifier.weight(1f)
            ) {
                Text("Add City")
            }

            Button(
                onClick = onFetchWeather,
                modifier = Modifier.weight(1f)
            ) {
                Text("Get Forecasts")
            }
        }
    }
}