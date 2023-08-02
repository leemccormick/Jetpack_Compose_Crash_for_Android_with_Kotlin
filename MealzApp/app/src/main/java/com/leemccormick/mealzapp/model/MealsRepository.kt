package com.leemccormick.mealzapp.model

import com.leemccormick.mealzapp.model.api.MealsWebService
import com.leemccormick.mealzapp.model.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    suspend fun getMeals(): MealsCategoriesResponse {
        return webService.getMeals()
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