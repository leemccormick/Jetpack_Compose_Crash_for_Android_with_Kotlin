package com.leemccormick.mealzapp.ui.details

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.leemccormick.mealzapp.model.response.MealResponse
import kotlin.math.min

@Composable
fun MealDetailsScreen(meal: MealResponse?) {
    // var isExpanded by remember { mutableStateOf(false) } // --> Before using enum
    // val imageSizeDp: Dp by animateDpAsState(targetValue = if (isExpanded) 200.dp else 100.dp)
    // var profilePictureState by remember { mutableStateOf(MealProfilePictureState.Normal) }
    // val transition = updateTransition(targetState = profilePictureState, label = "")
    // val imageSizeDp: Dp by transition.animateDp(targetValueByState = { it.size }, label = "")
    // val color by transition.animateColor(targetValueByState = { it.color }, label = "")
    // val widthSize: Dp by transition.animateDp(targetValueByState = { it.borderWidth }, label = "")
    // val scrollState = rememberScrollState()
    // val offSet = min(1f, 1-(scrollState.value / 600f))

    val color = Color.Green
    val widthSize: Dp = 2.dp
    val scrollState = rememberLazyListState()
    val offSet = min(
        1f,
        1 - (scrollState.firstVisibleItemScrollOffset / 600f + scrollState.firstVisibleItemScrollOffset)
    )
    val size by animateDpAsState(targetValue = max(100.dp, 200.dp * offSet))

    Surface(color = MaterialTheme.colors.background) {
        Column {
            Surface(elevation = 4.dp) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier.padding(16.dp),
                        shape = CircleShape,
                        border = BorderStroke(width = widthSize, color = color)
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = meal?.imageUrl,
                                builder = {
                                    transformations(CircleCropTransformation())
                                }
                            ),
                            contentDescription = "meal picture",
                            modifier = Modifier
                                .size(size)
                                .padding(8.dp)
                        )
                    }

                    Text(
                        text = meal?.name ?: "defaults name",
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }

            val dummyContentList = (0..100).map { it.toString() }
            // Column(modifier = Modifier.verticalScroll(scrollState)) { --> it is better to use LazyColumn
            LazyColumn(state = scrollState) {
                items(dummyContentList) { dummyItem ->
                    Text(
                        text = "This is a text element : $dummyItem",
                        modifier = Modifier.padding(32.dp)
                    )
                }
            }
            /*
            Button(onClick = {
                // isExpanded = !isExpanded //--> Before using enum
             profilePictureState =
                 if (profilePictureState == MealProfilePictureState.Normal) MealProfilePictureState.Expanded else MealProfilePictureState.Normal
            }) {
                Text("Change state of meal profile picture", modifier = Modifier.padding(16.dp))
            }
             */
        }
    }
}

// Using Enum for all animation
enum class MealProfilePictureState(val color: Color, val size: Dp, val borderWidth: Dp) {
    Normal(Color.Magenta, 120.dp, 8.dp),
    Expanded(Color.Green, 200.dp, 24.dp)
}