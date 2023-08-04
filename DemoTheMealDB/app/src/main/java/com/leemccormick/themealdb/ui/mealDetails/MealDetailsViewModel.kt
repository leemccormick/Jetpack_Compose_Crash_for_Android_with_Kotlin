package com.leemccormick.themealdb.ui.mealDetails

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leemccormick.themealdb.model.repository.MealsRepository
import com.leemccormick.themealdb.model.response.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealDetailsViewModel(private val repository: MealsRepository = MealsRepository.getInstance()) :
    ViewModel() {
    val mealState: MutableState<Meal?> = mutableStateOf(null)
    val ingredientsState: MutableState<List<String>> = mutableStateOf(emptyList())

    init {

        val selectedMeal = repository.getSelectedMeal()
        if (selectedMeal != null) {
            refreshMeal(selectedMeal)
        } else {
            refreshRandomMeal()
        }
    }

    fun refreshRandomMeal() {
        viewModelScope.launch(Dispatchers.IO) {
            val meal = getRandomMeal()
            mealState.value = meal
            ingredientsState.value = getIngredients()
        }
    }

    private fun refreshMeal(selectedMeal: Meal?) {
        mealState.value = selectedMeal
        ingredientsState.value = getIngredients()
    }

    private suspend fun getRandomMeal(): Meal? {
        return repository.getRandomMeal()
    }

    private fun getIngredients(): List<String> {
        val meal = mealState.value
        var ingredients: MutableList<String> = mutableListOf()

        if (!meal?.ingredient1.isNullOrEmpty()) {
            if (!meal?.measure1.isNullOrEmpty()) {
                ingredients.add("${meal?.measure1} ${meal?.ingredient1}")
            } else {
                ingredients.add("${meal?.ingredient1}")
            }
        }

        if (!meal?.ingredient2.isNullOrEmpty()) {
            if (!meal?.measure2.isNullOrEmpty()) {
                ingredients.add("${meal?.measure2} ${meal?.ingredient2}")
            } else {
                ingredients.add("${meal?.ingredient2}")
            }
        }

        if (!meal?.ingredient3.isNullOrEmpty()) {
            if (!meal?.measure3.isNullOrEmpty()) {
                ingredients.add("${meal?.measure3} ${meal?.ingredient3}")
            } else {
                ingredients.add("${meal?.ingredient3}")
            }
        }

        if (!meal?.ingredient4.isNullOrEmpty()) {
            if (!meal?.measure4.isNullOrEmpty()) {
                ingredients.add("${meal?.measure4} ${meal?.ingredient4}")
            } else {
                ingredients.add("${meal?.ingredient4}")
            }
        }

        if (!meal?.ingredient5.isNullOrEmpty()) {
            if (!meal?.measure5.isNullOrEmpty()) {
                ingredients.add("${meal?.measure5} ${meal?.ingredient5}")
            } else {
                ingredients.add("${meal?.ingredient5}")
            }
        }

        if (!meal?.ingredient6.isNullOrEmpty()) {
            if (!meal?.measure6.isNullOrEmpty()) {
                ingredients.add("${meal?.measure6} ${meal?.ingredient6}")
            } else {
                ingredients.add("${meal?.ingredient6}")
            }
        }

        if (!meal?.ingredient7.isNullOrEmpty()) {
            if (!meal?.measure7.isNullOrEmpty()) {
                ingredients.add("${meal?.measure7} ${meal?.ingredient7}")
            } else {
                ingredients.add("${meal?.ingredient7}")
            }
        }

        if (!meal?.ingredient8.isNullOrEmpty()) {
            if (!meal?.measure8.isNullOrEmpty()) {
                ingredients.add("${meal?.measure8} ${meal?.ingredient8}")
            } else {
                ingredients.add("${meal?.ingredient8}")
            }
        }

        if (!meal?.ingredient9.isNullOrEmpty()) {
            if (!meal?.measure9.isNullOrEmpty()) {
                ingredients.add("${meal?.measure9} ${meal?.ingredient9}")
            } else {
                ingredients.add("${meal?.ingredient9}")
            }
        }

        if (!meal?.ingredient10.isNullOrEmpty()) {
            if (!meal?.measure10.isNullOrEmpty()) {
                ingredients.add("${meal?.measure10} ${meal?.ingredient10}")
            } else {
                ingredients.add("${meal?.ingredient10}")
            }
        }

        if (!meal?.ingredient11.isNullOrEmpty()) {
            if (!meal?.measure11.isNullOrEmpty()) {
                ingredients.add("${meal?.measure11} ${meal?.ingredient11}")
            } else {
                ingredients.add("${meal?.ingredient11}")
            }
        }

        if (!meal?.ingredient12.isNullOrEmpty()) {
            if (!meal?.measure12.isNullOrEmpty()) {
                ingredients.add("${meal?.measure12} ${meal?.ingredient12}")
            } else {
                ingredients.add("${meal?.ingredient12}")
            }
        }

        if (!meal?.ingredient13.isNullOrEmpty()) {
            if (!meal?.measure13.isNullOrEmpty()) {
                ingredients.add("${meal?.measure13} ${meal?.ingredient13}")
            } else {
                ingredients.add("${meal?.ingredient13}")
            }
        }

        if (!meal?.ingredient14.isNullOrEmpty()) {
            if (!meal?.measure14.isNullOrEmpty()) {
                ingredients.add("${meal?.measure14} ${meal?.ingredient14}")
            } else {
                ingredients.add("${meal?.ingredient14}")
            }
        }

        if (!meal?.ingredient15.isNullOrEmpty()) {
            if (!meal?.measure15.isNullOrEmpty()) {
                ingredients.add("${meal?.measure15} ${meal?.ingredient15}")
            } else {
                ingredients.add("${meal?.ingredient15}")
            }
        }

        if (!meal?.ingredient16.isNullOrEmpty()) {
            if (!meal?.measure16.isNullOrEmpty()) {
                ingredients.add("${meal?.measure16} ${meal?.ingredient16}")
            } else {
                ingredients.add("${meal?.ingredient16}")
            }
        }

        if (!meal?.ingredient17.isNullOrEmpty()) {
            if (!meal?.measure17.isNullOrEmpty()) {
                ingredients.add("${meal?.measure17} ${meal?.ingredient17}")
            } else {
                ingredients.add("${meal?.ingredient17}")
            }
        }

        if (!meal?.ingredient18.isNullOrEmpty()) {
            if (!meal?.measure18.isNullOrEmpty()) {
                ingredients.add("${meal?.measure18} ${meal?.ingredient18}")
            } else {
                ingredients.add("${meal?.ingredient18}")
            }
        }

        if (!meal?.ingredient19.isNullOrEmpty()) {
            if (!meal?.measure19.isNullOrEmpty()) {
                ingredients.add("${meal?.measure19} ${meal?.ingredient19}")
            } else {
                ingredients.add("${meal?.ingredient19}")
            }
        }

        if (!meal?.ingredient20.isNullOrEmpty()) {
            if (!meal?.measure20.isNullOrEmpty()) {
                ingredients.add("${meal?.measure20} ${meal?.ingredient20}")
            } else {
                ingredients.add("${meal?.ingredient20}")
            }
        }
        return ingredients
    }
}