package com.leemccormick.themealdb.ui.filterMeals

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.leemccormick.themealdb.ui.theme.TheMealDBTheme

@Composable
fun FilterMealsScreen() {
    Text(text = "Hello Filter screen here...")
}

@Preview(showBackground = true)
@Composable
fun FilterMealsScreenDefaultPreview() {
    TheMealDBTheme {
        FilterMealsScreen()
    }
}
