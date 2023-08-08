package com.leemccormick.themealdb.ui.filterMeals

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leemccormick.themealdb.R
import com.leemccormick.themealdb.model.repository.MealsRepository
import com.leemccormick.themealdb.model.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class FilterMode(val index: Int, var searchTerm: String) {
    object Categories : FilterMode(0, "")
    object Area : FilterMode(1, "")
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

    fun getFilterModeBySelectedItem(name: String): FilterMode {
        filterMode.searchTerm = name
        return filterMode
    }

    fun getDrawableInt(country: Country): Int {
        when (country.countryName) {
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