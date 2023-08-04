package com.leemccormick.themealdb.ui.searchMeals

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leemccormick.themealdb.model.repository.MealsRepository
import com.leemccormick.themealdb.model.response.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchMealsViewModel(private val repository: MealsRepository = MealsRepository.getInstance()) :
    ViewModel() {
    private val randomSearchTerms = arrayOf(
        "a",
        "b",
        "c",
        "d",
        "e",
        "f",
        "g",
        "h",
        "i",
        "j",
        "k",
        "l",
        "m",
        "n",
        "o",
        "p",
        "q",
        "r",
        "s",
        "t",
        "u",
        "v",
        "w",
        "x",
        "y",
        "z"
    )
    private var searchTerm: String = ""
    private var isSearching = false
    val searchMealsState: MutableState<List<Meal>> = mutableStateOf(emptyList<Meal>())
    val searchTermState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue(""))

    init {
        val lastIndexToRandom = randomSearchTerms.size - 1
        val randomIndex = (0..lastIndexToRandom).random()
        searchTerm = randomSearchTerms[randomIndex]
        loadSearchMeal()
    }

    fun searchForMeals(value: TextFieldValue) {
        searchTermState.value = value
        searchTerm = value.text
        if (searchTerm.count() > 2 && !isSearching) {
            loadSearchMeal()
        }
    }

    private suspend fun getSearchMeals(): List<Meal> {
        return repository.getSearchMeals(searchTerm).meals
    }

    private fun loadSearchMeal() {
        viewModelScope.launch(Dispatchers.IO) {
            isSearching = true
            val searchMeals = getSearchMeals()
            if (searchMeals.isNullOrEmpty()) {
                isSearching = false
            } else {
                searchMealsState.value = searchMeals
                isSearching = false
            }
        }
    }
}