package com.leemccormick.themealdb.model.response

import com.google.gson.annotations.SerializedName

data class Area(
    @SerializedName("meals") val countries: List<Country>
)

data class Country(
    @SerializedName("strArea") val countryName: String
)

//List all Area
//www.themealdb.com/api/json/v1/1/list.php?a=list
/*
{
  "meals": [
    {
      "strArea": "American"
    },
    {
      "strArea": "British"
    },
    {
      "strArea": "Canadian"
    },
    {
      "strArea": "Chinese"
    },
    {
      "strArea": "Croatian"
    },
    {
      "strArea": "Dutch"
    },
    {
      "strArea": "Egyptian"
    },
    {
      "strArea": "Filipino"
    },
    {
      "strArea": "French"
    },
    {
      "strArea": "Greek"
    },
    {
      "strArea": "Indian"
    },
    {
      "strArea": "Irish"
    },
    {
      "strArea": "Italian"
    },
    {
      "strArea": "Jamaican"
    },
    {
      "strArea": "Japanese"
    },
    {
      "strArea": "Kenyan"
    },
    {
      "strArea": "Malaysian"
    },
    {
      "strArea": "Mexican"
    },
    {
      "strArea": "Moroccan"
    },
    {
      "strArea": "Polish"
    },
    {
      "strArea": "Portuguese"
    },
    {
      "strArea": "Russian"
    },
    {
      "strArea": "Spanish"
    },
    {
      "strArea": "Thai"
    },
    {
      "strArea": "Tunisian"
    },
    {
      "strArea": "Turkish"
    },
    {
      "strArea": "Unknown"
    },
    {
      "strArea": "Vietnamese"
    }
  ]
}
*/

//Filter by Area
//www.themealdb.com/api/json/v1/1/filter.php?a=Canadian
/*
{
  "meals": [
    {
      "strMeal": "Egyptian Fatteh",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/rlwcc51598734603.jpg",
      "idMeal": "53031"
    },
    {
      "strMeal": "Feteer Meshaltet",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/9f4z6v1598734293.jpg",
      "idMeal": "53030"
    },
    {
      "strMeal": "Ful Medames",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/lvn2d51598732465.jpg",
      "idMeal": "53025"
    },
    {
      "strMeal": "Koshari",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/4er7mj1598733193.jpg",
      "idMeal": "53027"
    },
    {
      "strMeal": "Mulukhiyah",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/x372ug1598733932.jpg",
      "idMeal": "53029"
    },
    {
      "strMeal": "Shakshuka",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/g373701551450225.jpg",
      "idMeal": "52963"
    },
    {
      "strMeal": "Shawarma",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/kcv6hj1598733479.jpg",
      "idMeal": "53028"
    },
    {
      "strMeal": "Tamiya",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/n3xxd91598732796.jpg",
      "idMeal": "53026"
    }
  ]
}
*/
