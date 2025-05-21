package com.rylov.weatherforecast.data

import com.rylov.weatherforecast.models.WeatherResponse

class WeatherRepository(private val apiService: WeatherApiService) {
    suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double,
        days: Int
    ): WeatherResponse? {
        return try {
            apiService.getWeatherForecast(
                latitude = latitude,
                longitude = longitude,
                days = days
            )
        } catch (e: Exception) {
            null
        }
    }
}