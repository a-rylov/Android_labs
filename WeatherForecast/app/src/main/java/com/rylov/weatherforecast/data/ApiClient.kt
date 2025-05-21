package com.rylov.weatherforecast.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val CITY_API_BASE_URL = "https://api.api-ninjas.com/v1/"
    private const val WEATHER_API_BASE_URL = "https://api.open-meteo.com/v1/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofitCity = Retrofit.Builder()
        .baseUrl(CITY_API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitWeather = Retrofit.Builder()
        .baseUrl(WEATHER_API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val cityApiService: CityApiService = retrofitCity.create(CityApiService::class.java)
    private val weatherApiService: WeatherApiService = retrofitWeather.create(WeatherApiService::class.java)

    val cityRepository: CityRepository by lazy { CityRepository(cityApiService) }
    val weatherRepository: WeatherRepository by lazy { WeatherRepository(weatherApiService) }
}