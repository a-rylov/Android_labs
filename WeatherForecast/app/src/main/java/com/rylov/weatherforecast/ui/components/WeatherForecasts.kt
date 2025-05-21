package com.rylov.weatherforecast.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rylov.weatherforecast.models.WeatherForecast

@Composable
fun WeatherForecasts(forecasts: List<WeatherForecast>) {
    if (forecasts.isNotEmpty()) {
        Column {
            Text("Weather Forecasts", style = MaterialTheme.typography.titleLarge)

            val groupedForecasts = forecasts.groupBy { it.city }

            LazyColumn {
                groupedForecasts.forEach { (city, cityForecasts) ->
                    item {
                        Column(
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Text(city, style = MaterialTheme.typography.titleMedium)
                            Column {
                                cityForecasts.forEach { forecast ->
                                    ForecastItem(forecast = forecast)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}