package com.rylov.flightssearch.data.repository

import com.rylov.flightssearch.data.dao.FavoriteDao
import com.rylov.flightssearch.data.model.Favorite
import kotlinx.coroutines.flow.Flow

class FavoriteRepository(private val favoriteDao: FavoriteDao) {
    fun getFavorites(): Flow<List<Favorite>> = favoriteDao.getAllFavorites()

    suspend fun insertFavorite(favorite: Favorite) = favoriteDao.insert(favorite)

    suspend fun removeFavorite(departure: String, destination: String) =
        favoriteDao.delete(departure, destination)
}