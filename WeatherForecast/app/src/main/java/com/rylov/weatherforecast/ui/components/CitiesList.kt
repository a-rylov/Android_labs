package com.rylov.weatherforecast.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CitiesList(
    cities: List<String>,
    onRemoveCity: (String) -> Unit
) {
    if (cities.isNotEmpty()) {
        Column {
            Text("Selected cities:", style = MaterialTheme.typography.titleSmall)
            LazyColumn {
                items(cities) { city ->
                    CityItem(city = city, onRemove = onRemoveCity)
                }
            }
        }
    }
}