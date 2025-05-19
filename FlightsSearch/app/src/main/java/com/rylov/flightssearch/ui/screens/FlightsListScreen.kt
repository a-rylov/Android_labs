package com.rylov.flightssearch.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rylov.flightssearch.ui.viewmodels.FlightsViewmodel
import com.rylov.flightssearch.ui.components.FlightItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightListScreen(
    viewModel: FlightsViewmodel,
    onBackClick: () -> Unit
) {
    val flights by viewModel.flights.collectAsState()
    val selectedAirport by viewModel.selectedAirport.collectAsState()
    val favorites by viewModel.favorites.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        selectedAirport?.let {
                            "Рейсы из ${it.iataCode}"
                        } ?: "Избранные рейсы"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                }
            )
        }
    ) { padding ->
        if (flights.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Нет доступных рейсов")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(flights) { flight ->
                    val isFavorite = favorites.any {
                        it.departureCode == flight.departureCode &&
                                it.destinationCode == flight.destinationCode
                    }

                    FlightItem(
                        flight = flight,
                        isFavorite = isFavorite,
                        onFavoriteClick = {
                            if (isFavorite) {
                                viewModel.removeFavorite(
                                    flight.departureCode,
                                    flight.destinationCode
                                )
                            } else {
                                viewModel.addFavorite(
                                    flight.departureCode,
                                    flight.destinationCode
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}