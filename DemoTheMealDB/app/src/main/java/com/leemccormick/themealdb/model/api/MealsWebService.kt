package com.leemccormick.themealdb.model.api

import com.leemccormick.themealdb.model.response.Meal
import com.leemccormick.themealdb.model.response.Meals
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MealsWebService {
    private lateinit var api: MealsApi
    private val baseUrl = "https://www.themealdb.com/api/json/v1/1/"

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MealsApi::class.java)
    }

    suspend fun getRandomMeal(): Meal? {
        return api.getRandomMeal().meals.firstOrNull()
    }

    interface MealsApi {
        @GET("random.php") // https://www.themealdb.com/api/json/v1/1/random.php
        suspend fun getRandomMeal(): Meals
    }
}