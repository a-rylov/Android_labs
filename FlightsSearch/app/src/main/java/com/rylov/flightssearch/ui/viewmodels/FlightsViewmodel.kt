package com.rylov.flightssearch.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rylov.flightssearch.data.model.Airport
import com.rylov.flightssearch.data.model.Favorite
import com.rylov.flightssearch.data.model.Flight
import com.rylov.flightssearch.data.repository.AirportRepository
import com.rylov.flightssearch.data.repository.FavoriteRepository
import com.rylov.flightssearch.data.repository.SearchPrefsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FlightsViewmodel(
    private val airportRepository: AirportRepository,
    private val favoriteRepository: FavoriteRepository,
    private val searchPreferencesRepository: SearchPrefsRepository
) : ViewModel() {
    private val _airports = MutableStateFlow<List<Airport>>(emptyList())
    val airports: StateFlow<List<Airport>> = _airports

    private val _favorites = MutableStateFlow<List<Favorite>>(emptyList())
    val favorites: StateFlow<List<Favorite>> = _favorites

    private val _flights = MutableStateFlow<List<Flight>>(emptyList())
    val flights: StateFlow<List<Flight>> = _flights

    private val _selectedAirport = MutableStateFlow<Airport?>(null)
    val selectedAirport: StateFlow<Airport?> = _selectedAirport

    private val _searchQuery = MutableStateFlow<String>("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        loadFavorites()
        loadSearchQuery()
    }

    private fun loadSearchQuery() {
        viewModelScope.launch {
            searchPreferencesRepository.searchQuery.collect { query ->
                _searchQuery.value = query
                if (query.isNotEmpty()) {
                    searchAirports(query)
                }
            }
        }
    }

    fun searchAirports(query: String) {
        viewModelScope.launch {
            _searchQuery.value = query
            searchPreferencesRepository.saveSearchQuery(query)
            airportRepository.searchAirports(query).collect { airports ->
                _airports.value = airports
            }
        }
    }

    fun loadFavorites() {
        viewModelScope.launch {
            favoriteRepository.getFavorites().collect { favorites ->
                _favorites.value = favorites
            }
        }
    }

    fun addFavorite(departure: String, destination: String) {
        viewModelScope.launch {
            favoriteRepository.insertFavorite(
                Favorite(
                    departureCode = departure,
                    destinationCode = destination
                )
            )
            loadFavorites()
        }
    }

    fun removeFavorite(departure: String, destination: String) {
        viewModelScope.launch {
            favoriteRepository.removeFavorite(departure, destination)
            loadFavorites()
        }
    }

    fun generateFlightsForAirport(airport: Airport) {
        viewModelScope.launch {
            airportRepository.generateFlightsForAirports().collect { flights ->
                val filteredFlights = flights.filter { flight ->
                    flight.departureCode == airport.iataCode
                }
                _flights.value = filteredFlights
            }
        }
    }

    fun selectAirport(airport: Airport) {
        _selectedAirport.value = airport
    }

    fun getAirportByCode(code: String): Airport? {
        return airports.value.find { it.iataCode == code }
    }
}