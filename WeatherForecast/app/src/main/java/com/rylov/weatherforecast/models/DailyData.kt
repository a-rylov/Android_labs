package com.rylov.weatherforecast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyData(
    @SerialName("time") val time: List<String>,
    @SerialName("temperature_2m_max") val temperature_2m_max: List<Double>,
    @SerialName("temperature_2m_min") val temperature_2m_min: List<Double>
)