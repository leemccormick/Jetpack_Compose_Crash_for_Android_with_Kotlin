package com.leemccormick.themealdb.ui.filterMeals

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leemccormick.themealdb.R
import com.leemccormick.themealdb.model.repository.MealsRepository
import com.leemccormick.themealdb.model.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class FilterMode(val index: Int, var searchTerm: String, var imageUrl: String) {
    object Categories : FilterMode(0, "", "")
    object Area : FilterMode(1, "", "")
}

class FilterMealsViewModel(private val repository: MealsRepository = MealsRepository.getInstance()) :
    ViewModel() {
    val countriesState: MutableState<List<Country>> = mutableStateOf(emptyList<Country>())
    val categoriesState: MutableState<List<Category>> = mutableStateOf(emptyList<Category>())
    val selectedTabIndexState: MutableState<Int> = mutableStateOf(0)
    val tabList = listOf("Categories", "Area")
    var filterMode: FilterMode = FilterMode.Categories

    init {
        loadArea()
        loadCategories()
    }

    fun updateSelectedTabIndex(index: Int) {
        selectedTabIndexState.value = index
        filterMode = if (index == 0) {
            FilterMode.Categories
        } else {
            FilterMode.Area
        }
    }

    fun setFilterModeBySelectedCategory(category: Category) {
        filterMode.imageUrl = category.imageUrl
    }

    fun getFilterModeBySelectedItem(name: String): FilterMode {
        filterMode.searchTerm = name
        return filterMode
    }

    fun getDrawableInt(country: Country): Int {
        return repository.getDrawableInt(country.countryName)
    }

    private suspend fun getArea(): Area {
        return repository.getArea()
    }

    private suspend fun getCategories(): Categories {
        return repository.getCategories()
    }

    private fun loadArea() {
        viewModelScope.launch(Dispatchers.IO) {
            val area = getArea()
            countriesState.value = area.countries
        }
    }

    private fun loadCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val categories = getCategories()
            categoriesState.value = categories.categories
        }
    }
}