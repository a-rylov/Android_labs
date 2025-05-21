package com.rylov.weatherforecast.ui.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rylov.weatherforecast.models.WeatherForecast
import kotlinx.coroutines.launch
import com.rylov.weatherforecast.data.CityRepository
import com.rylov.weatherforecast.data.WeatherRepository

class WeatherViewModel(
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    private val _weatherForecasts = mutableStateListOf<WeatherForecast>()
    val weatherForecasts: List<WeatherForecast> = _weatherForecasts

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    private val _cities = mutableStateListOf<String>()
    val cities: List<String> = _cities

    fun addCity(city: String) {
        if (city.isNotBlank() && !_cities.contains(city)) {
            _cities.add(city)
        }
    }

    fun removeCity(city: String) {
        _cities.remove(city)
        _weatherForecasts.removeAll { it.city == city }
    }

    private suspend fun fetchWeatherForecast(city: String, days: Int) {
        _isLoading.value = true
        _errorMessage.value = null

        try {
            val cityInfo = cityRepository.getCityCoordinates(city)
            if (cityInfo == null) {
                _errorMessage.value = "City not found"
                return
            }

            val weatherResponse = weatherRepository.getWeatherForecast(
                latitude = cityInfo.latitude,
                longitude = cityInfo.longitude,
                days = days
            ) ?: run {
                _errorMessage.value = "Failed to get weather data"
                return
            }

            _weatherForecasts.removeAll { it.city == city }

            weatherResponse.daily.time.forEachIndexed { index, date ->
                _weatherForecasts.add(
                    WeatherForecast(
                        city = city,
                        date = date,
                        maxTemp = weatherResponse.daily.temperature_2m_max[index],
                        minTemp = weatherResponse.daily.temperature_2m_min[index]
                    )
                )
            }
        } catch (e: Exception) {
            _errorMessage.value = "Error fetching data: ${e.message}"
        } finally {
            _isLoading.value = false
        }
    }

    fun fetchAllCitiesForecasts(days: Int) {
        viewModelScope.launch {
            _cities.forEach { city ->
                fetchWeatherForecast(city, days)
            }
        }
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }
}