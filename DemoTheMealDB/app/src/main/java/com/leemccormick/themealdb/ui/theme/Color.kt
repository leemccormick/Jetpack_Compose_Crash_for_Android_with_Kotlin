package com.leemccormick.themealdb.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val DarkestBrownMealDB = Color(0xFF23180d)
val DarkBrownBgMealDB = Color(0xFF2d2013)
val OrangeTextMealDB = Color(0xFFfda846)

// Extension to use the color in Activity --> color = MaterialTheme.colors.darkestBrown
val Color.darkBrown: Color
    @Composable
    get() = DarkBrownBgMealDB

val Color.darkestBrown: Color
    @Composable
    get() = DarkestBrownMealDB

val Color.lightOrange: Color
    @Composable
    get() = OrangeTextMealDB