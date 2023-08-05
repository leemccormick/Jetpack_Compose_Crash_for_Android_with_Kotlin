package com.leemccormick.themealdb.ui.filterMeals

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.leemccormick.themealdb.ui.searchMeals.MealCardView
import com.leemccormick.themealdb.ui.searchMeals.SearchMealsViewModel
import com.leemccormick.themealdb.ui.searchMeals.SearchView
import com.leemccormick.themealdb.ui.theme.TheMealDBTheme

//TODO: Build UI For this screen...
@Composable
fun FilterMealsScreen() {
    val viewModel: FilterMealsViewModel = viewModel()
    // val countries = viewModel.countriesState.value
    val categories = viewModel.categoriesState.value
    var meals = viewModel.mealsState.value
    var mealDetails = viewModel.mealDetailsState.value

    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Text(text = "meals : ${meals.size}", color = Color.White)
            }

            item {
                Text(text = "Details : ${mealDetails?.name}", color = Color.White)
            }
//            items(countries) { country ->
//                Text(text = country.countryName, color = Color.White)
//            }

            items(categories) { category ->
                Text(text = category.name, color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilterMealsScreenDefaultPreview() {
    TheMealDBTheme {
        FilterMealsScreen()
    }
}
