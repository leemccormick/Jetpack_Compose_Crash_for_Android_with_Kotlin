package com.leemccormick.themealdb.ui.filterMeals

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leemccormick.themealdb.model.repository.MealsRepository
import com.leemccormick.themealdb.model.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//TODO: Build more logic  For Filter screen here...
class FilterMealsViewModel(private val repository: MealsRepository = MealsRepository.getInstance()) :
    ViewModel() {

    val countriesState: MutableState<List<Country>> = mutableStateOf(emptyList<Country>())
    val categoriesState: MutableState<List<Category>> = mutableStateOf(emptyList<Category>())
    val mealsState: MutableState<List<Meal>> = mutableStateOf(emptyList<Meal>())
    val mealDetailsState: MutableState<Meal?> = mutableStateOf(null)

    init {
        // loadArea()
        // loadMealsByCategoryName("beef")
        loadCategories()
    }

    private suspend fun getArea(): Area {
        return repository.getArea()
    }

    private suspend fun getMealsByArea(countryName: String): Meals {
        return repository.getMealsByArea(countryName)
    }

    private suspend fun getCategories(): Categories {
        return repository.getCategories()
    }

    private suspend fun getMealsByCategory(category: String): Meals {
        return repository.getMealsByCategory(category)
    }

    private suspend fun getMealById(id: String): Meal? {
        return repository.getMealById(id)
    }

    private fun loadArea() {
        viewModelScope.launch(Dispatchers.IO) {
            val area = getArea()
            countriesState.value = area.countries

            loadMealsByCountry(area.countries[0].countryName)


        }
    }

    private fun loadCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val categories = getCategories()
            categoriesState.value = categories.categories

            loadMealsByCategoryName(categories.categories[0].name)
        }
    }

    private fun loadMealsByCountry(countryName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val meals = getMealsByArea(countryName)
            mealsState.value = meals.meals

            val testId = meals.meals[0].id

            Log.d("TAG", "testId : $testId")
            loadMealDetailsById(testId)
        }
    }

    private fun loadMealsByCategoryName(categoryName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val meals = getMealsByCategory(categoryName)
            mealsState.value = meals.meals

            val testId = meals.meals[0].id

            Log.d("TAG", "testId : $testId")
            loadMealDetailsById(testId)
        }
    }

    private fun loadMealDetailsById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val meal = getMealById(id)
            Log.d("TAG", "meal : $meal")
            mealDetailsState.value = meal
        }
    }
}