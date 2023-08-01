package com.leemccormick.profile_card.ui.theme

import android.provider.CalendarContract
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val veryLightGray = Color(0x68DCDCDC)
val lightGreen200 = Color(0x9932CD32)

// Extension to use the color in Activity --> color = MaterialTheme.colors.lightGreen
val Colors.lightGreen: Color
    @Composable
get() = lightGreen200