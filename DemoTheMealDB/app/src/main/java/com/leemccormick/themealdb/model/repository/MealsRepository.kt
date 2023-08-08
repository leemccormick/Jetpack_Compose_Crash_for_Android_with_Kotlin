package com.leemccormick.themealdb.model.repository

import com.leemccormick.themealdb.model.api.MealsWebService
import com.leemccormick.themealdb.model.response.Area
import com.leemccormick.themealdb.model.response.Categories
import com.leemccormick.themealdb.model.response.Meal
import com.leemccormick.themealdb.model.response.Meals
import com.leemccormick.themealdb.ui.filterMeals.FilterMode

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    private var cachedRandomMeal: Meal? = null
    private var cachedSearchMeals: Meals? = null
    private var cachedSelectedMeal: Meal? = null
    private var cachedArea: Area? = null
    private var cachedMealsByArea: Meals? = null
    private var cachedCategories: Categories? = null
    private var cachedMealsByCategory: Meals? = null
    private var cachedMealById: Meal? = null
    private var cachedFilterMode: FilterMode? = null

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

    suspend fun getMealById(id: String): Meal? {
        val response = webService.getMealById(id)
        cachedMealById = response
        return response
    }

    suspend fun getArea(): Area {
        val response = webService.getListAllArea()
        cachedArea = response
        return response
    }

    suspend fun getMealsByArea(countryName: String): Meals {
        val response = webService.getMealsByArea(countryName)
        cachedMealsByArea = response
        return response
    }

    suspend fun getCategories(): Categories {
        val response = webService.getListAllCategories()
        cachedCategories = response
        return response
    }

    suspend fun getMealsByCategory(category: String): Meals {
        val response = webService.getMealsByCategory(category)
        cachedMealsByCategory = response
        return response
    }

    fun savedSelectedMeal(mealId: String?) {
        cachedSelectedMeal = cachedSearchMeals?.meals?.firstOrNull { it.id == mealId }
    }

    fun savedSelectedFilterMode(filter: FilterMode) {
        cachedFilterMode = filter
    }

    fun getSelectedMeal(): Meal? {
        return cachedSelectedMeal
    }

    fun getSelectedFilterMode(): FilterMode? {
        return cachedFilterMode
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