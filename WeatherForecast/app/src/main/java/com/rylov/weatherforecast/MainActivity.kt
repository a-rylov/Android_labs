package com.rylov.weatherforecast

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.rylov.weatherforecast.data.ApiClient
import com.rylov.weatherforecast.ui.screens.MainScreen
import com.rylov.weatherforecast.ui.theme.WeatherForecastTheme
import com.rylov.weatherforecast.ui.screens.WeatherViewModel
import com.rylov.weatherforecast.ui.screens.WeatherViewModelFactory

class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels {
        WeatherViewModelFactory(
            ApiClient.cityRepository,
            ApiClient.weatherRepository
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherForecastTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(viewModel)
                }
            }
        }
    }
}