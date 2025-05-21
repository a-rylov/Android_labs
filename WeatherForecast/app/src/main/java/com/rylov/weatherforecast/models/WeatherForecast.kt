package com.rylov.weatherforecast.models

import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecast(
    val city: String,
    val date: String,
    val maxTemp: Double,
    val minTemp: Double
)