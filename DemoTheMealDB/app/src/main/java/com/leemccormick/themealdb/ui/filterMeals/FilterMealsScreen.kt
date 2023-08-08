package com.leemccormick.themealdb.ui.filterMeals

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.leemccormick.themealdb.model.response.Category
import com.leemccormick.themealdb.model.response.Country
import com.leemccormick.themealdb.ui.theme.TheMealDBTheme


@Composable
fun FilterMealsScreen(navigationCallback: (FilterMode) -> Unit) {
    val viewModel: FilterMealsViewModel = viewModel()

    BoxWithConstraints() {
        val heightOfScreen = maxHeight - 60.dp
        Surface(color = MaterialTheme.colors.surface, modifier = Modifier.fillMaxSize()) {
            LazyColumn() {
                item {
                    CustomTabs(viewModel)
                }

                item {
                    if (viewModel.selectedTabIndexState.value == 0) {
                        CategoriesListView(viewModel, heightOfScreen, navigationCallback)
                    } else {
                        AreaListView(viewModel, heightOfScreen, navigationCallback)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoriesListView(
    viewModel: FilterMealsViewModel,
    heightOfGrid: Dp,
    navigationCallback: (FilterMode) -> Unit
) {
    val categories = viewModel.categoriesState.value

    LazyVerticalGrid(
        modifier = Modifier
            .padding(16.dp)
            .heightIn(0.dp, heightOfGrid),
        cells = GridCells.Fixed(2),
        content = {
            items(categories) { category ->
                CategoryView(viewModel, category, navigationCallback)
            }
        }
    )
}

@Composable
fun CategoryView(
    viewModel: FilterMealsViewModel,
    category: Category,
    navigationCallback: (FilterMode) -> Unit
) {
    Card(elevation = 5.dp, backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                BorderStroke(1.dp, MaterialTheme.colors.onSecondary),
                RoundedCornerShape(12.dp)
            )
            .clickable {
                navigationCallback(viewModel.getFilterModeBySelectedItem("${category.name}"))
            }
    ) {
        Column(
            Modifier
                .size(220.dp)
                .padding(8.dp)
        ) {
            Image(
                painter = rememberImagePainter(category.imageUrl),
                contentDescription = "category picture",
                modifier = Modifier
                    .size(160.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "${category.name}",
                modifier = Modifier.fillMaxSize(),
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AreaListView(
    viewModel: FilterMealsViewModel,
    heightOfGrid: Dp,
    navigationCallback: (FilterMode) -> Unit
) {
    val countries = viewModel.countriesState.value

    LazyVerticalGrid(
        modifier = Modifier
            .padding(16.dp)
            .heightIn(0.dp, heightOfGrid),
        cells = GridCells.Fixed(2),
        content = {
            items(countries) { country ->
                CountryView(viewModel, country, navigationCallback)
            }
        }
    )
}

@Composable
fun CountryView(
    viewModel: FilterMealsViewModel,
    country: Country,
    navigationCallback: (FilterMode) -> Unit
) {
    Card(elevation = 5.dp, backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                BorderStroke(1.dp, MaterialTheme.colors.onSecondary),
                RoundedCornerShape(12.dp)
            )
            .clickable {
                navigationCallback(viewModel.getFilterModeBySelectedItem("${country.countryName}"))
            }
    ) {
        Column(
            Modifier
                .size(220.dp)
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = viewModel.getDrawableInt(country)),
                contentDescription = "country picture",
                modifier = Modifier
                    .size(160.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "${country.countryName}",
                modifier = Modifier.fillMaxSize(),
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}

@Composable
fun CustomTabs(viewModel: FilterMealsViewModel) {
    TabRow(selectedTabIndex = viewModel.selectedTabIndexState.value,
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        indicator = { tabPositions: List<TabPosition> ->
            Box {}
        }
    ) {
        viewModel.tabList.forEachIndexed { index, text ->
            val selected = viewModel.selectedTabIndexState.value == index
            Tab(
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .padding(top = 10.dp, bottom = 0.dp, start = 10.dp, end = 10.dp),

                selected = selected,
                onClick = { viewModel.updateSelectedTabIndex(index) },
                text = {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.h4,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxSize()
                            .bottomBorder(
                                if (selected) 2.dp else 0.dp,
                                if (selected) MaterialTheme.colors.onSecondary else MaterialTheme.colors.background
                            )
                    )
                }
            )
        }
    }
}

@Composable
fun Modifier.bottomBorder(strokeWidth: Dp, color: Color) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = size.width
            val height = size.height - strokeWidthPx / 2

            drawLine(
                color = color,
                start = Offset(x = 0f, y = height),
                end = Offset(x = width, y = height),
                strokeWidth = strokeWidthPx
            )
        }
    }
)

@Preview(showBackground = true)
@Composable
fun FilterMealsScreenDefaultPreview() {
    TheMealDBTheme {
        FilterMealsScreen { }
    }
}