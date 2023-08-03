package com.leemccormick.mealzapp.ui.meals

import android.media.Image
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.leemccormick.mealzapp.model.response.MealResponse
import com.leemccormick.mealzapp.ui.theme.MealzAppTheme

@Composable
fun MealsCategoriesScreen(navigationCallback: (String) -> Unit) {
    val viewModel: MealsCategoriesViewModel = viewModel()
    val meals = viewModel.mealsState.value

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(meals) { meal ->
            MealsCategory(meal, navigationCallback)
        }
    }
}

@Composable
fun MealsCategory(meal: MealResponse, navigationCallback: (String) -> Unit) {
    var isExpanded by remember  { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable {
                navigationCallback(meal.id)
            }
    ) {
        Row(modifier = Modifier.animateContentSize()) {
            Image(
                painter = rememberImagePainter(meal.imageUrl),
                contentDescription = "meal picture",
                modifier = Modifier
                    .size(88.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterVertically),
            )

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.8f) // To fill 80% of view
                    .padding(16.dp)
            ) {
                Text(
                    text = meal.name,
                    style = MaterialTheme.typography.h6
                )

                Text(
                    text = meal.description,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.subtitle2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray,
                    maxLines = if (isExpanded) 10 else 4
                )
            }

            Icon(
                imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = "Expand row icon",
                modifier = Modifier
                    .padding(16.dp)
                    .align(if (isExpanded) Alignment.Bottom else Alignment.CenterVertically)
                    .clickable { isExpanded = !isExpanded }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealzAppTheme {
        MealsCategoriesScreen( { } )
    }
}
