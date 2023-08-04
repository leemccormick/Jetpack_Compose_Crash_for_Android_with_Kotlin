package com.leemccormick.themealdb

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.leemccormick.themealdb.model.repository.MealsRepository
import com.leemccormick.themealdb.ui.filterMeals.FilterMealsScreen
import com.leemccormick.themealdb.ui.mealDetails.MealDetailsScreen
import com.leemccormick.themealdb.ui.mealDetails.MealDetailsViewModel
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
    Search("destination_search_meals"),
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
            SearchMealsScreen() { mealId ->
                val testRoute = "${Screen.Detail.destination}/$mealId"
                Log.d("TAG", "$testRoute")
                navController.navigate("${Screen.Detail.destination}/$mealId")

            }
        }

        composable(
            route = "${Screen.Detail.destination}/{meal_id}",
            arguments = listOf(navArgument("meal_id") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            backStackEntry.arguments?.let {
                val mealId = it.getString("meal_id")
                MealsRepository.getInstance().savedSelectedMeal(mealId)
                MealDetailsScreen() { screen ->
                    navController.navigate(screen.destination)
                }
            }
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