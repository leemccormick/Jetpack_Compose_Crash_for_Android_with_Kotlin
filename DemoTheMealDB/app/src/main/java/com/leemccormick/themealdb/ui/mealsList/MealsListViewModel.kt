package com.leemccormick.themealdb.ui.mealsList

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leemccormick.themealdb.model.repository.MealsRepository
import com.leemccormick.themealdb.model.response.Meal
import com.leemccormick.themealdb.model.response.Meals
import com.leemccormick.themealdb.ui.filterMeals.FilterMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealsListViewModel(private val repository: MealsRepository = MealsRepository.getInstance()) :
    ViewModel() {
    val mealsState: MutableState<List<Meal>> = mutableStateOf(emptyList<Meal>())
    val mealDetailsState: MutableState<Meal?> = mutableStateOf(null)
    val titleState: MutableState<String?> = mutableStateOf(null)

    init {
        val filter = repository.getSelectedFilterMode()
        filter?.let {
            titleState.value = "Meals from ${filter.searchTerm}"
            if (filter == FilterMode.Categories) {
                loadMealsByCategoryName(filter.searchTerm)
            } else {
                loadMealsByCountry(filter.searchTerm)
            }
        }
    }

    private suspend fun getMealsByArea(countryName: String): Meals {
        return repository.getMealsByArea(countryName)
    }

    private suspend fun getMealsByCategory(category: String): Meals {
        return repository.getMealsByCategory(category)
    }

    private suspend fun getMealById(id: String): Meal? {
        return repository.getMealById(id)
    }


    private fun loadMealsByCountry(countryName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val meals = getMealsByArea(countryName)
            mealsState.value = meals.meals
        }
    }

    private fun loadMealsByCategoryName(categoryName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val meals = getMealsByCategory(categoryName)
            mealsState.value = meals.meals
        }
    }

    private fun loadMealDetailsById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val meal = getMealById(id)
            mealDetailsState.value = meal
        }
    }
}