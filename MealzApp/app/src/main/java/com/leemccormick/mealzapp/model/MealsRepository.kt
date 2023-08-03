package com.leemccormick.mealzapp.model

import com.leemccormick.mealzapp.model.api.MealsWebService
import com.leemccormick.mealzapp.model.response.MealResponse
import com.leemccormick.mealzapp.model.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    private var cachedMeals = listOf<MealResponse>()

    suspend fun getMeals(): MealsCategoriesResponse {
        val response = webService.getMeals()
        cachedMeals = response.categories
        return response
    }

    fun getMeal(id: String): MealResponse? {
        return cachedMeals.firstOrNull { it.id == id }
    }

    // Singleton
    companion object {
        @Volatile
        private var instance: MealsRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: MealsRepository().also { instance = it }
        }
    }
}

/* Before using call back method
class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    fun getMeals() {
        return webService.getMeals().execute()
            .body() // Bad Practice!! APP CRASH on AndroidRuntime: FATAL EXCEPTION: main -->  This one is async task, need to do in call back later...
    }
}
*/

/* Before adding Coroutines using this code
class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    fun getMeals(successCallback: (response: MealsCategoriesResponse?) -> Unit) {
        return webService.getMeals().enqueue(object : Callback<MealsCategoriesResponse> {
            override fun onResponse(
                call: Call<MealsCategoriesResponse>,
                response: Response<MealsCategoriesResponse>
            ) {
                if (response.isSuccessful)
                    successCallback(response.body())
            }

            override fun onFailure(call: Call<MealsCategoriesResponse>, t: Throwable) {
                // TODO treat failure
            }
        })
    }
}
 */