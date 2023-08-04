package com.leemccormick.themealdb.model.repository

import com.leemccormick.themealdb.model.api.MealsWebService
import com.leemccormick.themealdb.model.response.Meal
import com.leemccormick.themealdb.model.response.Meals

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    private var cachedRandomMeal: Meal? = null
    private var cachedSearchMeals: Meals? = null
    private var cachedSelectedMeal: Meal? = null

    suspend fun getRandomMeal(): Meal? {
        val response = webService.getRandomMeal()
        cachedRandomMeal = response
        return response
    }

    suspend fun getSearchMeals(searchTeam: String): Meals {
        val response = webService.getSearchMeals(searchTeam)
        cachedSearchMeals = response
        return response
    }

    fun savedSelectedMeal(mealId: String?) {
        cachedSelectedMeal = cachedSearchMeals?.meals?.firstOrNull { it.id == mealId }
    }

    fun getSelectedMeal(): Meal? {
        return cachedSelectedMeal
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