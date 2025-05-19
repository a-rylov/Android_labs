package com.rylov.flightssearch.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.rylov.flightssearch.ui.viewmodels.FlightsViewmodel
import com.rylov.flightssearch.data.model.Airport
import com.rylov.flightssearch.data.model.Flight
import com.rylov.flightssearch.ui.components.AirportItem
import com.rylov.flightssearch.ui.components.FlightItem

@Composable
fun SearchScreen(
    viewModel: FlightsViewmodel,
    onAirportSelected: (Airport) -> Unit
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val airports by viewModel.airports.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { viewModel.searchAirports(it) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Поиск аэропорта") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = { focusManager.clearFocus() }
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        when {
            searchQuery.isEmpty() && favorites.isNotEmpty() -> {
                Text(
                    text = "Избранные направления",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(favorites) { favorite ->
                        val departureAirport = viewModel.getAirportByCode(favorite.departureCode)
                        val destinationAirport =
                            viewModel.getAirportByCode(favorite.destinationCode)

                        if (departureAirport != null && destinationAirport != null) {
                            val fakeFlight = Flight(
                                departureCode = favorite.departureCode,
                                destinationCode = favorite.destinationCode,
                                departureAirport = departureAirport.name,
                                destinationAirport = destinationAirport.name
                            )

                            FlightItem(
                                flight = fakeFlight,
                                isFavorite = true,
                                onFavoriteClick = {
                                    viewModel.removeFavorite(
                                        fakeFlight.departureCode,
                                        fakeFlight.destinationCode
                                    )
                                }
                            )
                        }
                    }
                }
            }

            searchQuery.isEmpty() && favorites.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Введите код или название аэропорта")
                }
            }

            else -> {
                LazyColumn {
                    items(airports) { airport ->
                        AirportItem(
                            airport = airport,
                            onClick = { onAirportSelected(airport) }
                        )
                    }
                }
            }
        }
    }
}