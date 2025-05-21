package com.rylov.weatherforecast.models

import kotlinx.serialization.Serializable

@Serializable
data class CityData(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val country: String
)