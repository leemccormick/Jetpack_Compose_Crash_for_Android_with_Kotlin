package com.leemccormick.themealdb.ui.searchMeals

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.leemccormick.themealdb.model.response.Meal
import com.leemccormick.themealdb.ui.theme.TheMealDBTheme

@Composable
fun SearchMealsScreen(navigationCallback: (String) -> Unit) {
    val viewModel: SearchMealsViewModel = viewModel()
    val searchMeals = viewModel.searchMealsState.value

    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                SearchView(viewModel)
            }

            items(searchMeals) { meal ->
                MealCardView(meal = meal, navigationCallback)
            }
        }
    }
}

@Composable
fun SearchView(viewModel: SearchMealsViewModel) {
    TextField(
        value = viewModel.searchTermState.value,
        onValueChange = { value ->
            viewModel.searchForMeals(value)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                BorderStroke(0.5.dp, Color.DarkGray),
                RoundedCornerShape(8.dp)
            ),
        textStyle = MaterialTheme.typography.caption,
        placeholder = {
            Text(
                text = "Search for meals...",
                style = MaterialTheme.typography.caption,
                color = Color.DarkGray
            )
        },
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp),
                tint = MaterialTheme.colors.onSecondary,
            )
        },
        trailingIcon = {
            if (viewModel.searchTermState.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        viewModel.searchTermState.value = TextFieldValue("")
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp),
                        tint = MaterialTheme.colors.onSecondary,
                    )
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = MaterialTheme.colors.onSecondary,
            leadingIconColor = MaterialTheme.colors.onSecondary,
            trailingIconColor = MaterialTheme.colors.onSecondary,
            backgroundColor = MaterialTheme.colors.primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@SuppressLint("LongLogTag")
@Composable
fun MealCardView(meal: Meal, navigationCallback: (String) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navigationCallback(meal.id) }
            .border(
                BorderStroke(0.5.dp, Color.DarkGray),
                RoundedCornerShape(8.dp)
            )
    ) {
        Row() {
            Image(
                painter = rememberImagePainter(meal.imageUrl),
                contentDescription = "meal picture",
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        BorderStroke(1.dp, MaterialTheme.colors.onSecondary),
                        RoundedCornerShape(12.dp)
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.9f) // To fill 80% of view
                    .padding(8.dp)
            ) {
                Text(
                    text = meal.name,
                    style = MaterialTheme.typography.h3
                )

                MealDetailDescriptionView("${meal?.category}", Icons.Default.Check)
                MealDetailDescriptionView("${meal?.area}", Icons.Default.LocationOn)
            }

            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "Expand row icon",
                tint = MaterialTheme.colors.onSecondary,
                modifier = Modifier
                    .padding(0.dp)
                    .align(Alignment.CenterVertically)
                    .clickable { navigationCallback(meal.id) }
            )
        }
    }
}

@Composable
fun MealDetailDescriptionView(description: String, icon: ImageVector) {
    Row() {
        Icon(
            imageVector = icon,
            contentDescription = "description icon",
            tint = MaterialTheme.colors.onSecondary,
            modifier = Modifier
                .padding(top = 0.dp, bottom = 0.dp, start = 0.dp, end = 4.dp)
                .align(Alignment.CenterVertically)
                .size(16.dp)
        )

        Text(
            text = description,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis,
            color = Color.LightGray,
            maxLines = 4
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchMealsScreenDefaultPreview() {
    TheMealDBTheme {
        SearchMealsScreen({})
    }
}
