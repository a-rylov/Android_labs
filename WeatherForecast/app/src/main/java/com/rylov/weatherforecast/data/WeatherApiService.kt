package com.rylov.weatherforecast.data

import com.rylov.weatherforecast.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("daily") daily: String = "temperature_2m_max,temperature_2m_min",
        @Query("forecast_days") days: Int = 7,
        @Query("timezone") timezone: String = "auto"
    ): WeatherResponse
}