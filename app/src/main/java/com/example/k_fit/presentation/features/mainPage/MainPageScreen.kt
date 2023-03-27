package com.example.k_fit.presentation.features.mainPage

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.k_fit.R
import com.example.k_fit.presentation.components.CustomSearchBar
import com.example.k_fit.presentation.components.CustomWorkoutCard
import com.example.k_fit.presentation.features.models.WorkoutUIModel

@Composable
fun MainPage(
    userProfile: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    val workoutList: List<WorkoutUIModel> = listOf(
        WorkoutUIModel(
            name = "Incline hammer curls",
            type = "Strength",
            difficulty = "Intermediate",
            muscle = "biceps",
            equipment = "TODO",
            instructions = LoremIpsum(30).values.joinToString(),
            image = R.drawable.example
        ),
        WorkoutUIModel(
            name = "Wide-grip barbell curl",
            type = "Strength",
            difficulty = "Beginner",
            muscle = "biceps",
            equipment = "TODO",
            instructions = LoremIpsum(30).values.joinToString(),
            image = R.drawable.example
        ),
        WorkoutUIModel(
            name = "Cheat curl",
            type = "Strength",
            difficulty = "Expert",
            muscle = "biceps",
            equipment = "TODO",
            instructions = LoremIpsum(30).values.joinToString(),
            image = R.drawable.example
        )
    )

    val contextForToast = LocalContext.current.applicationContext
    val modifier: Modifier = Modifier
    Column(modifier = modifier.pointerInput(Unit) {
        detectTapGestures(onTap = {
            focusManager.clearFocus()
        })
    }) {
        TopAppBar(
            title = {
                Text(text = "Workout")
            },
            navigationIcon = {
                IconButton(onClick = {
                    Toast.makeText(contextForToast, "Navigation Icon Click", Toast.LENGTH_SHORT)
                        .show()
                }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Navigation icon")
                }
            },
            modifier = modifier.background(Color(R.color.surface3)),
            actions = {
                IconButton(onClick = {userProfile()}) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Navigation icon"
                    )
                }
            }
        )
        Spacer(modifier = modifier.height(8.dp))
        CustomSearchBar(modifier = modifier)
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .clipToBounds()
        ) {
            items(workoutList) { workout ->
                CustomWorkoutCard(
                    workoutCardName = workout.name,
                    workout = workout
                )
            }
        }
    }
}


@Preview("Preview of the main Page of the application", showSystemUi = true)
@Composable
fun PreviewMainPage() {
    MainPage({})
}