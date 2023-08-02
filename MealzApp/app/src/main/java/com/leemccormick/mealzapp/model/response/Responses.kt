package com.leemccormick.mealzapp.model.response

import com.google.gson.annotations.SerializedName

data class MealsCategoriesResponse(val categories: List<MealResponse>)

// To Use SerializedName for naming, implementation 'com.google.code.gson:gson:2.8.6' on build.gradle
data class MealResponse(
    @SerializedName("idCategory") val id: String,
    @SerializedName("strCategory") val name: String,
    @SerializedName("strCategoryDescription") val description: String,
    @SerializedName("strCategoryThumb") val imageUrl: String
)