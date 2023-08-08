package com.leemccormick.themealdb.ui.mealsList

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.leemccormick.themealdb.ui.filterMeals.AreaListView
import com.leemccormick.themealdb.ui.filterMeals.CategoriesListView
import com.leemccormick.themealdb.ui.filterMeals.CustomTabs
import com.leemccormick.themealdb.ui.filterMeals.FilterMealsViewModel

@Composable
fun MealsListScreen() {
    val viewModel: MealsListViewModel = viewModel()
    val meals = viewModel.mealsState.value
    val title = viewModel.titleState.value

    BoxWithConstraints() {
        val heightOfScreen = maxHeight - 60.dp
        Surface(color = MaterialTheme.colors.surface, modifier = Modifier.fillMaxSize()) {
            LazyColumn() {
                item {
                    Text(text = "$title")
                }

                item {
                    Text(text = "$meals --> ${meals.size}")
                }
            }
        }
    }
}