package com.leemccormick.themealdb.model.api

import com.leemccormick.themealdb.model.response.Area
import com.leemccormick.themealdb.model.response.Categories
import com.leemccormick.themealdb.model.response.Meal
import com.leemccormick.themealdb.model.response.Meals
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

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

    suspend fun getSearchMeals(searchTerm: String): Meals {
        return api.getSearchMeals(searchTerm)
    }

    suspend fun getMealById(id: String): Meal? {
        return api.getMealById(id).meals.firstOrNull()
    }

    suspend fun getListAllArea(): Area {
        return api.getListAllArea("list")
    }

    suspend fun getMealsByArea(countryName: String): Meals {
        return api.getMealsByArea(countryName)
    }

    suspend fun getListAllCategories(): Categories {
        return api.getListAllCategories()
    }

    suspend fun getMealsByCategory(category: String): Meals {
        return api.getMealsByCategory(category)
    }

    interface MealsApi {
        @GET("random.php") // https://www.themealdb.com/api/json/v1/1/random.php
        suspend fun getRandomMeal(): Meals

        @GET("search.php") // https://www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata
        suspend fun getSearchMeals(@Query("s") string: String): Meals

        @GET("lookup.php") // https://www.themealdb.com/api/json/v1/1/lookup.php?i=53026
        suspend fun getMealById(@Query("i") string: String): Meals

        @GET("list.php") // https://www.themealdb.com/api/json/v1/1/list.php?a=list
        suspend fun getListAllArea(@Query("a") string: String): Area

        @GET("categories.php") // https://www.themealdb.com/api/json/v1/1/categories.php
        suspend fun getListAllCategories(): Categories

        @GET("filter.php") // https://www.themealdb.com/api/json/v1/1/filter.php?a=Canadian
        suspend fun getMealsByArea(@Query("a") string: String): Meals

        @GET("filter.php") // https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood
        suspend fun getMealsByCategory(@Query("c") string: String): Meals
    }
}