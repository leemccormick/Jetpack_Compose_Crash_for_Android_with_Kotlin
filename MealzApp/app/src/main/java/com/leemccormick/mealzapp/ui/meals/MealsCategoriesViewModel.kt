package com.leemccormick.mealzapp.ui.meals

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leemccormick.mealzapp.model.MealsRepository
import com.leemccormick.mealzapp.model.response.MealResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository()) :
    ViewModel() {

    // private val mealsJob = Job() --> if needed to create own scope, but using viewModelScope is better

    init {
        // val scope = CoroutineScope(mealsJob + Dispatchers.IO) --> To create your own scope, but using viewModelScope is better

        Log.d("TAG_COROUTINES", "we are about to launch a coroutine.")

        viewModelScope.launch(Dispatchers.IO) {

            Log.d("TAG_COROUTINES", "we have launched the coroutine.")

            val meals = getMeals()

            Log.d("TAG_COROUTINES", "we have received the async data.")

            mealsState.value = meals
        }

        Log.d("TAG_COROUTINES", "other work")
    }

    val mealsState: MutableState<List<MealResponse>> = mutableStateOf(emptyList<MealResponse>())

    suspend fun getMeals(): List<MealResponse> {
        return repository.getMeals().categories
    }

    // viewModelScope --> Don't need to clear it, Use this block below if create own scope
    /*
    override fun onCleared() {
        super.onCleared()
        mealsJob.cancel()
    }
   */
}

/* Example of Using successCallback
class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository()) :
    ViewModel() {
    /* Return for sync function, not work for api
    fun getMeals(): List<MealResponses> {
      return repository.getMeals()?.categories.orEmpty()
    }
    */

    fun getMeals(successCallback: (response: MealsCategoriesResponses?) -> Unit) {
        repository.getMeals { response ->
            successCallback(response)
        }
    }
}
*/

/* Before adding Coroutines using this code
class MealsCategoriesViewModel (private val repository: MealsRepository = MealsRepository()): ViewModel() {
    fun getMeals(successCallback: (response: MealsCategoriesResponse?) -> Unit) {
        repository.getMeals { response ->
            successCallback(response)
        }
    }
}
 */
