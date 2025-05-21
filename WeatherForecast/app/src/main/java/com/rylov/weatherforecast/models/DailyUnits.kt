package com.rylov.weatherforecast.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyUnits(
    @SerialName("time") val time: String,
    @SerialName("temperature_2m_max") val temperature_2m_max: String,
    @SerialName("temperature_2m_min") val temperature_2m_min: String
)