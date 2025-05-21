package com.rylov.weatherforecast.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rylov.weatherforecast.data.ApiClient
import com.rylov.weatherforecast.ui.components.CitiesList
import com.rylov.weatherforecast.ui.components.ErrorMessage
import com.rylov.weatherforecast.ui.components.InputSection
import com.rylov.weatherforecast.ui.components.WeatherForecasts

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    viewModel: WeatherViewModel = viewModel(
        factory = WeatherViewModelFactory(
            ApiClient.cityRepository,
            ApiClient.weatherRepository
        )
    )
) {
    var cityName by remember { mutableStateOf("") }
    var days by remember { mutableIntStateOf(7) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        viewModel.errorMessage.value?.let { message ->
            ErrorMessage(message) {
                viewModel.clearErrorMessage()
            }
        }

        InputSection(
            cityName = cityName,
            days = days,
            onCityNameChange = { cityName = it },
            onDaysChange = { days = it },
            onAddCity = {
                viewModel.addCity(it)
                cityName = ""
            },
            onFetchWeather = { viewModel.fetchAllCitiesForecasts(days) }
        )

        CitiesList(
            cities = viewModel.cities,
            onRemoveCity = { viewModel.removeCity(it) }
        )

        if (viewModel.isLoading.value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            WeatherForecasts(forecasts = viewModel.weatherForecasts)
        }
    }
}