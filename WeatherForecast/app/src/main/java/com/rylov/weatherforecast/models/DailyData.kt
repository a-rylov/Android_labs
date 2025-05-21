package com.rylov.weatherforecast.models

import kotlinx.serialization.Serializable

@Serializable
data class DailyData(
    val time: List<String>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>
)