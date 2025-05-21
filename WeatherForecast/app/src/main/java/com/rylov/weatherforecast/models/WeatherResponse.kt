package com.rylov.weatherforecast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("generationtime_ms") val generationtime_ms: Double,
    @SerialName("utc_offset_seconds") val utc_offset_seconds: Int,
    @SerialName("timezone") val timezone: String,
    @SerialName("timezone_abbreviation") val timezone_abbreviation: String,
    @SerialName("elevation") val elevation: Double,
    @SerialName("daily_units") val daily_units: DailyUnits,
    @SerialName("daily") val daily: DailyData
)