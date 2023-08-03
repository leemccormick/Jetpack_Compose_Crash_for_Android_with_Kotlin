package com.leemccormick.mealzapp.ui.details

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.leemccormick.mealzapp.model.MealsRepository
import com.leemccormick.mealzapp.model.response.MealResponse

class MealDetailsViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val repository: MealsRepository = MealsRepository.getInstance()
    var mealState = mutableStateOf<MealResponse?>(null)

    init {
        val mealId = savedStateHandle.get<String>("meal_category_id")?: ""
        mealState.value = repository.getMeal(mealId)
    }
}