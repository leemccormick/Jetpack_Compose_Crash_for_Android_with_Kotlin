package com.leemccormick.themealdb

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leemccormick.themealdb.ui.filterMeals.FilterMealsScreen
import com.leemccormick.themealdb.ui.mealDetails.MealDetailsScreen
import com.leemccormick.themealdb.ui.searchMeals.SearchMealsScreen
import com.leemccormick.themealdb.ui.theme.TheMealDBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMealDBTheme {
                TheMealDBApp()
            }
        }
    }
}

enum class Screen(val destination: String) {
    Detail("destination_meal_details"),
    Filter("destination_filter_meals"),
    Search("destination_search_meals")
}

@Composable
private fun TheMealDBApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Detail.destination
    ) {
        composable(route = Screen.Detail.destination) {
            MealDetailsScreen() { screen ->
                navController.navigate(screen.destination)
            }
        }

        composable(route = Screen.Filter.destination) {
            FilterMealsScreen()
        }

        composable(route = Screen.Search.destination) {
            SearchMealsScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TheMealDBTheme {
        TheMealDBApp()
    }
}