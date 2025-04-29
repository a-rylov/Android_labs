package com.rylov.mycity.model

data class Recommendation(
    val id: Int,
    val name: String,
    val description: String,
    val imageRes: Int? = null,
    val address: String,
    val rating: Float
)