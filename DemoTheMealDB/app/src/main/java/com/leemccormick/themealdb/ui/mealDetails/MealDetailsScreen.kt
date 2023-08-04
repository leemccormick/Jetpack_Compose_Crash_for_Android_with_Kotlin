package com.leemccormick.themealdb.ui.mealDetails

import android.util.Log
import android.widget.GridView
import android.widget.ImageView
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.leemccormick.themealdb.Screen
import com.leemccormick.themealdb.model.response.Meal
import com.leemccormick.themealdb.ui.searchMeals.MealDetailDescriptionView
import com.leemccormick.themealdb.ui.theme.TheMealDBTheme

@Composable
fun MealDetailsScreen(navigationCallback: (Screen) -> Unit) {
    val viewModel: MealDetailsViewModel = viewModel()
    val meal = viewModel.mealState.value
    val ingredients = viewModel.ingredientsState.value
    Scaffold(
        topBar = {
            AppBar("TheMealDB", viewModel, navigationCallback)
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                MealNameView("${meal?.name}")
                MealDetailsScrollView(meal, ingredients)
            }
        }
    }
}

@Composable
fun MealNameView(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.h5,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 8.dp)
    )
}

@Composable
fun MealDetailsScrollView(meal: Meal?, ingredients: List<String>) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        MealImageView(meal?.imageUrl)
        MealDescriptionsView(meal)
        MealIngredientsView(ingredients)
        MealInstructionsView("${meal?.instructions}")
    }
}

@Composable
fun MealDescriptionsView(meal: Meal?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 0.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MealDetailDescriptionView("${meal?.category}", Icons.Default.Check)
        MealDetailDescriptionView("${meal?.area}", Icons.Default.LocationOn)
    }
}

@Composable
fun MealImageView(imageUrl: String?) {
    Image(
        painter = rememberImagePainter(imageUrl),
        contentDescription = "meal picture",
        modifier = Modifier
            .size(300.dp)
            .border(
                BorderStroke(2.dp, MaterialTheme.colors.onSecondary),
                RoundedCornerShape(24.dp)
            )
            .clip(RoundedCornerShape(24.dp))
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MealIngredientsView(ingredients: List<String>) {
    if (!ingredients.isNullOrEmpty()) {
        MealHeaderView("Ingredients", Icons.Filled.Notifications)
        Box {
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(16.dp)
                    .heightIn(0.dp, 300.dp),
                cells = GridCells.Fixed(2),
                content = {
                    items(ingredients) { ingredient ->
                        Text(
                            text = "$ingredient",
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun MealInstructionsView(instructions: String) {
    MealHeaderView("Instructions", Icons.Filled.Menu)
    Text(
        text = instructions,
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun MealHeaderView(title: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 0.dp, start = 16.dp, end = 16.dp),

        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "header icon",
            tint = MaterialTheme.colors.onSecondary,
            modifier = Modifier
                .padding(top = 0.dp, bottom = 0.dp, start = 0.dp, end = 8.dp)
                .align(Alignment.CenterVertically)
                .size(26.dp)
        )

        Text(
            text = title,
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun AppBar(title: String, viewModel: MealDetailsViewModel, navigationCallback: (Screen) -> Unit) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onSecondary
            )
        },
        actions = {
            IconButton(onClick = {
                navigationCallback(Screen.Search)
            }) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colors.onSecondary
                )

            }

            IconButton(onClick = {
                navigationCallback(Screen.Filter)
            }) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "List",
                    tint = MaterialTheme.colors.onSecondary
                )
            }

            IconButton(onClick = {
                viewModel.refreshRandomMeal()
            }) {
                Icon(
                    imageVector = Icons.Outlined.Refresh,
                    contentDescription = "Refresh",
                    tint = MaterialTheme.colors.onSecondary
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MealDetailsScreenDefaultPreview() {
    TheMealDBTheme {
        MealDetailsScreen({})
    }
}