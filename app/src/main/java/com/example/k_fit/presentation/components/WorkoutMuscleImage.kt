package com.example.k_fit.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.k_fit.presentation.features.models.WorkoutUIModel
import com.example.k_fit.ui.theme.CardBackground

@Composable
fun WorkoutMusclesImage(workout: WorkoutUIModel, modifier: Modifier) {
    Image(
        painter = painterResource(id = workout.image),
        contentDescription = "Image of a body with highlighted ${workout.muscle}",
        contentScale = ContentScale.FillHeight,
        colorFilter = ColorFilter.tint(color = CardBackground, blendMode = BlendMode.Multiply),
        modifier = modifier
            .size(80.dp)
    )
}