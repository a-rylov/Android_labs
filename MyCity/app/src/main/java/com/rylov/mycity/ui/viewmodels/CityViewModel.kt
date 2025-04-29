package com.rylov.mycity.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.rylov.mycity.model.Category
import com.rylov.mycity.model.Recommendation
import com.rylov.mycity.repository.CityRepository

class CityViewModel : ViewModel() {
    private val repository = CityRepository

    val categories: List<Category> = repository.getCategories()

    fun getRecommendations(categoryId: Int): List<Recommendation> {
        return repository.getRecommendations(categoryId)
    }

    fun getRecommendation(id: Int): Recommendation? {
        return repository.getRecommendationById(id)
    }
}