package com.rylov.weatherforecast.data

import com.rylov.weatherforecast.models.CityData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CityApiService {
    @GET("city")
    suspend fun getCityInfo(
        @Query("name") name: String,
        @Header("X-Api-Key") apiKey: String = "kpjd10AvE8sYNF38pD0T0w==eDyrp4VnmZeSTZfq" // Replace with your key
    ): List<CityData>
}