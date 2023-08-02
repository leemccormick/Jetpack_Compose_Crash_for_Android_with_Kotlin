package com.leemccormick.profilecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.leemccormick.profilecard.ui.theme.MyTheme
import com.leemccormick.profilecard.ui.theme.lightGreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                UsersApplication()
            }
        }
    }
}

@Composable
fun UsersApplication(userProfiles: List<UserProfile> = userProfileList) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "users_list") {
        composable(route = "users_list") {
            UserListScreen(userProfiles, navController)
        }

        composable("user_details/{userId}",
            arguments = listOf(
                navArgument(name = "userId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.let { UserProfileDetailScreen(userId = it.getInt("userId"), navController) }
        }
    }
}

@Composable
fun UserListScreen(
    userProfiles: List<UserProfile> = userProfileList,
    navController: NavHostController?
) {
    Scaffold(
        topBar = {
            AppBar("User List", Icons.Default.Home) { }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            LazyColumn() {
                items(userProfiles) { userProfile ->
                    ProfileCard(userProfile) {
                        navController?.navigate("user_details/${userProfile.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun AppBar(title: String, icon: ImageVector, iconClickAction: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "content description",
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable { iconClickAction.invoke() }
            )
        },
        title = { Text(title) }
    )
}

@Composable
fun ProfileCard(userProfile: UserProfile, clickAction: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable { clickAction.invoke() },
        elevation = 8.dp,
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(userProfile.photoUrl, userProfile.status, 72.dp)
            ProfileContent(userProfile.name, userProfile.status, Alignment.Start)
        }
    }
}

/* // This block help to load image faster, but did not work on this version.
     val asyncImage = loadImageResource(id = drawableId)
     asyncImage.resource.resource?.let { bitmap ->
         Image(
             bitmap = bitmap,
             modifier = Modifier.size(72.dp),
             contentScale = ContentScale.Crop
         )
     }
 */

/* This is for loading image before using coil library
Image(
    painter = painterResource(id = drawableId),
    contentDescription = "Content Description",
    modifier = Modifier.size(72.dp),
    contentScale = ContentScale.Crop
)
*/

@Composable
fun ProfilePicture(photoUrl: String, onlineStatus: Boolean, imageSize: Dp) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            color = if (onlineStatus) MaterialTheme.colors.lightGreen else Color.Red
        ),
        modifier = Modifier.padding(16.dp),
        elevation = 4.dp
    ) {
        Image(
            painter = rememberImagePainter(
                data = photoUrl,
                builder = {
                    transformations(CircleCropTransformation())
                },
            ),
            modifier = Modifier.size(imageSize),
            contentDescription = "Profile picture description",
        )
    }
}

@Composable
fun ProfileContent(userName: String, onlineStatus: Boolean, alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = alignment
    ) {
        Text(
            text = userName,
            style = MaterialTheme.typography.h5,
            color = if (onlineStatus) Color.Black else  Color.Gray
        )

        Text(
            text = if (onlineStatus) "Active Now" else "Offline",
            style = MaterialTheme.typography.body2,
            color = Color.Gray
        )
    }
}

@Composable
fun UserProfileDetailScreen(userId: Int, navController: NavHostController?) {
    val userProfile = userProfileList.first { userProfile -> userId == userProfile.id }
    Scaffold(
        topBar = {
            AppBar("User Profile Details", Icons.Default.ArrowBack) {
                navController?.navigateUp()
            }
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                ProfilePicture(userProfile.photoUrl, userProfile.status, 240.dp)
                ProfileContent(userProfile.name, userProfile.status, Alignment.CenterHorizontally)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileDetailPreview() {
    MyTheme {
        UserProfileDetailScreen(userId = 0, null)
    }
}

@Preview(showBackground = true)
@Composable
fun UserListPreview() {
    MyTheme {
        UserListScreen(userProfileList, null)
    }
}
