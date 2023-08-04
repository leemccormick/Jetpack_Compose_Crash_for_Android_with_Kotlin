package com.leemccormick.themealdb.ui.searchMeals

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.leemccormick.themealdb.ui.theme.TheMealDBTheme

@Composable
fun SearchMealsScreen() {
    Text(text = "Hello Search screen here...")
}

@Preview(showBackground = true)
@Composable
fun SearchMealsScreenDefaultPreview() {
    TheMealDBTheme {
        SearchMealsScreen()
    }
}
