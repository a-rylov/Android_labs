package com.rylov.flightssearch.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rylov.flightssearch.data.dao.AirportDao
import com.rylov.flightssearch.data.dao.FavoriteDao
import com.rylov.flightssearch.data.repository.AirportRepository
import com.rylov.flightssearch.data.repository.FavoriteRepository
import com.rylov.flightssearch.data.repository.SearchPrefsRepository

class FlightsViewmodelFactory(
    private val airportDao: AirportDao,
    private val favoriteDao: FavoriteDao,
    private val searchPreferencesRepository: SearchPrefsRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlightsViewmodel::class.java)) {
            val airportRepository = AirportRepository(airportDao)
            val favoriteRepository = FavoriteRepository(favoriteDao)
            return FlightsViewmodel(
                airportRepository,
                favoriteRepository,
                searchPreferencesRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}