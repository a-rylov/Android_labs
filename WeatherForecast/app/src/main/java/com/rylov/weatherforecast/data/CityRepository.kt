package com.rylov.weatherforecast.data

import com.rylov.weatherforecast.models.CityData

class CityRepository(private val apiService: CityApiService) {
    suspend fun getCityCoordinates(cityName: String): CityData? {
        return try {
            val response = apiService.getCityInfo(cityName)
            response.firstOrNull()
        } catch (e: Exception) {
            null
        }
    }
}