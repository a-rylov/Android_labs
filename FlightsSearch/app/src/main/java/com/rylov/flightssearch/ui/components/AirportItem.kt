package com.rylov.flightssearch.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rylov.flightssearch.data.model.Airport

@Composable
fun AirportItem(
    airport: Airport,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "${airport.iataCode} - ${airport.name}",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Пассажиров: ${airport.passengers}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}