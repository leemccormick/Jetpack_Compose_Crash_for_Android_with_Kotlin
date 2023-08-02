package com.leemccormick.mealzapp.model.api

import com.leemccormick.mealzapp.model.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// To create api using retrofit, need 2 implementations on build.gradle
/*
    - implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    - implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
*/

class MealsWebService {

    private lateinit var api: MealsApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MealsApi::class.java)
    }

    suspend fun getMeals(): MealsCategoriesResponse {
        return api.getMeals()
    }

    interface MealsApi {
        @GET("categories.php")
        suspend fun getMeals(): MealsCategoriesResponse
    }
}

/* Before adding Coroutines using this code
class MealsWebService {

    private lateinit var api: MealsApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MealsApi::class.java)
    }

    fun getMeals(): Call<MealsCategoriesResponse> {
        return api.getMeals()
    }

    interface MealsApi {
        @GET("categories.php")
        fun getMeals(): Call<MealsCategoriesResponse>
    }
}
 */

