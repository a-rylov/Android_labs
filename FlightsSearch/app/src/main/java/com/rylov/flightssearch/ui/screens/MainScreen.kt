package com.rylov.flightssearch.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rylov.flightssearch.ui.viewmodels.FlightsViewmodel

@Composable
fun FlightSearchApp(viewModel: FlightsViewmodel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "search"
    ) {
        composable("search") {
            SearchScreen(
                viewModel = viewModel,
                onAirportSelected = { airport ->
                    viewModel.selectAirport(airport)
                    viewModel.generateFlightsForAirport(airport)
                    navController.navigate("flights")
                }
            )
        }
        composable("flights") {
            FlightListScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
