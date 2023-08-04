package com.leemccormick.themealdb.model.repository

import com.leemccormick.themealdb.model.api.MealsWebService
import com.leemccormick.themealdb.model.response.Meal

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    private var cachedRandomMeal: Meal? = null

    suspend fun getRandomMeal(): Meal? {
        val response = webService.getRandomMeal()
        cachedRandomMeal = response
        return response
    }

    // This companion object for Singleton
    companion object {
        @Volatile
        private var instance: MealsRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: MealsRepository().also { instance = it }
        }
    }
}