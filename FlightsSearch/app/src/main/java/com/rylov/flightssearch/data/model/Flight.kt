package com.rylov.flightssearch.data.model

data class Flight(
    val departureCode: String,
    val destinationCode: String,
    val departureAirport: String,
    val destinationAirport: String
)