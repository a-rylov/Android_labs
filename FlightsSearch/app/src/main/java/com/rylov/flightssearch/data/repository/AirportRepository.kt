package com.rylov.flightssearch.data.repository

import com.rylov.flightssearch.data.dao.AirportDao
import com.rylov.flightssearch.data.model.Airport
import com.rylov.flightssearch.data.model.Flight
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class AirportRepository(private val airportDao: AirportDao) {


    fun searchAirports(query: String): Flow<List<Airport>> {
        val formattedQuery = "%$query%"
        return airportDao.searchAirports(formattedQuery)
    }

    fun generateFlightsForAirports(): Flow<List<Flight>> = flow {
        val airports = getAllAirports()

        val flights = mutableListOf<Flight>()

        for (departureAirport in airports) {
            for (destinationAirport in airports) {
                if (departureAirport.id != destinationAirport.id) {
                    val flight = Flight(
                        departureCode = departureAirport.iataCode,
                        destinationCode = destinationAirport.iataCode,
                        departureAirport = departureAirport.name,
                        destinationAirport = destinationAirport.name
                    )
                    flights.add(flight)
                }
            }
        }
        emit(flights)
    }

    private suspend fun getAllAirports(): List<Airport> {
        return airportDao.getAll().first()
    }
}