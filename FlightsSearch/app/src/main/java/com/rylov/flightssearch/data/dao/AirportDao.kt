package com.rylov.flightssearch.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.rylov.flightssearch.data.model.Airport
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {
    @Query("SELECT * FROM airport WHERE name LIKE '%' || :query || '%' OR iata_code LIKE '%' || :query || '%' ORDER BY passengers DESC")
    fun searchAirports(query: String): Flow<List<Airport>>

    @Query("SELECT * FROM airport WHERE iata_code = :code LIMIT 1")
    suspend fun getAirportByCode(code: String): Airport?

    @Query("SELECT * FROM airport")
    fun getAll(): Flow<List<Airport>>
}