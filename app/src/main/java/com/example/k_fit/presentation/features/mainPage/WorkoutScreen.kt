package com.example.k_fit.presentation.features.mainPage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.k_fit.R
import com.example.k_fit.presentation.components.CustomSearchBar
import com.example.k_fit.presentation.components.CustomWorkoutCard
import com.example.k_fit.presentation.features.models.WorkoutUIModel

@Composable
fun WorkoutScreen(paddingValues: PaddingValues, modifier: Modifier) {

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
    Column(modifier = modifier){
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
