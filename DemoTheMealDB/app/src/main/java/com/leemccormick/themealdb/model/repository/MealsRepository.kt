package com.leemccormick.themealdb.model.repository

import android.util.Log
import com.leemccormick.themealdb.R
import com.leemccormick.themealdb.model.api.MealsWebService
import com.leemccormick.themealdb.model.response.*
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
        cachedSelectedMeal = null
        cachedMealById = response
        Log.d("TEST", " at 36  getCachedMealById $cachedMealById")
        Log.d("TEST", " at 37  getCachedMealById ${response?.name}")

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

    fun getDrawableInt(countryName: String): Int {
        when (countryName) {
            "American" -> return R.drawable.us
            "British" -> return R.drawable.gb
            "Canadian" -> return R.drawable.ca
            "Chinese" -> return R.drawable.cn
            "Croatian" -> return R.drawable.hr
            "Dutch" -> return R.drawable.nl
            "Egyptian" -> return R.drawable.eg
            "Filipino" -> return R.drawable.ph
            "French" -> return R.drawable.fr
            "Greek" -> return R.drawable.gr
            "Indian" -> return R.drawable.`in`
            "Irish" -> return R.drawable.ie
            "Italian" -> return R.drawable.it
            "Jamaican" -> return R.drawable.jm
            "Japanese" -> return R.drawable.jp
            "Kenyan" -> return R.drawable.kn
            "Malaysian" -> return R.drawable.my
            "Mexican" -> return R.drawable.mx
            "Moroccan" -> return R.drawable.ma
            "Polish" -> return R.drawable.pl
            "Portuguese" -> return R.drawable.pt
            "Russian" -> return R.drawable.ru
            "Spanish" -> return R.drawable.es
            "Thai" -> return R.drawable.th
            "Tunisian" -> return R.drawable.tn
            "Turkish" -> return R.drawable.tr
            "Unknown" -> return R.drawable.question
            "Vietnamese" -> return R.drawable.vn
            else -> return R.drawable.question
        }
    }

    fun getSelectedMeal(): Meal? {
        return cachedSelectedMeal
    }

    fun getCachedMealById(): Meal? {
        return cachedMealById
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