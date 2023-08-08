package com.leemccormick.themealdb.ui.mealsList

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.leemccormick.themealdb.model.response.Category
import com.leemccormick.themealdb.model.response.Meal
import com.leemccormick.themealdb.ui.filterMeals.*
import com.leemccormick.themealdb.ui.mealDetails.MealDetailsScreen
import com.leemccormick.themealdb.ui.mealDetails.MealNameView
import com.leemccormick.themealdb.ui.theme.TheMealDBTheme

@Composable
fun MealsListScreen(navigationCallback: (String) -> Unit) {
    val viewModel: MealsListViewModel = viewModel()

    BoxWithConstraints() {
        val heightOfScreen = maxHeight - 60.dp
        Surface(color = MaterialTheme.colors.surface, modifier = Modifier.fillMaxSize()) {
            LazyColumn() {
                item {
                    MealsListHeaderView(viewModel)
                }

                item {
                    MealsListView(viewModel, heightOfScreen, navigationCallback)
                }
            }
        }
    }
}

@Composable
fun MealsListHeaderView(viewModel: MealsListViewModel) {
    val title = viewModel.titleState.value
    val filterMode = viewModel.filterMode

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 0.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (filterMode == FilterMode.Categories) {
            Image(
                painter = rememberImagePainter(filterMode.imageUrl),
                contentDescription = "category picture",
                modifier = Modifier.size(50.dp)
            )
        } else {
            Image(
                painter = painterResource(id = viewModel.getPainterResource()),
                contentDescription = "country picture",
                modifier = Modifier.size(50.dp)
            )
        }

        MealNameView(
            "$title",
            Modifier.padding(start = 8.dp, end = 0.dp, top = 0.dp, bottom = 0.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MealsListView(
    viewModel: MealsListViewModel,
    heightOfGrid: Dp,
    navigationCallback: (String) -> Unit
) {
    val meals = viewModel.mealsState.value

    LazyVerticalGrid(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 0.dp)
            .heightIn(0.dp, heightOfGrid),
        cells = GridCells.Fixed(2),
        content = {
            items(meals) { meal ->
                MealCardView(viewModel, meal, navigationCallback)
            }
        }
    )
}

@Composable
fun MealCardView(
    viewModel: MealsListViewModel,
    meal: Meal,
    navigationCallback: (String) -> Unit
) {
    Card(
        elevation = 5.dp, backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                BorderStroke(1.dp, MaterialTheme.colors.onSecondary),
                RoundedCornerShape(12.dp)
            )
            .clickable {
                viewModel.loadMealDetailsById(meal.id, navigationCallback)
            },
    ) {
        Column(
            modifier = Modifier
                .size(220.dp)
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(meal.imageUrl),
                contentDescription = "category picture",
                modifier = Modifier
                    .size(160.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp)
            )

            Text(
                text = "${meal.name}",
                modifier = Modifier.fillMaxSize(),
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealsListViewScreenDefaultPreview() {
    TheMealDBTheme {
        MealsListScreen({})
    }
}