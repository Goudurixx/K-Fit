package com.example.k_fit.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.k_fit.presentation.common.Gender
import com.example.k_fit.presentation.components.cardComponent.CardContentSubTitle
import com.example.k_fit.presentation.components.cardComponent.CardContentTitle
import com.example.k_fit.presentation.components.cardComponent.CardSubTitle
import com.example.k_fit.presentation.components.cardComponent.CardTitle
import com.example.k_fit.presentation.models.UserProfileUIModel
import com.example.k_fit.presentation.models.WorkoutUIModel
import com.example.k_fit.ui.theme.CardBackground
import com.example.k_fit.ui.theme.CardStroke

@Composable
fun CustomWorkoutCard(
    workoutCardName: String,
    user: UserProfileUIModel,
    workout: WorkoutUIModel
) {
    var openCard by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween()
            )
            .padding(15.dp)
            .clickable(
                onClick = { openCard = !openCard },
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(width = 1.dp, color = CardStroke),
        backgroundColor = CardBackground,
        elevation = 10.dp
    ) {
        if (openCard) {
            CustomWorkoutCardOpened(
                workoutCardName = workoutCardName,
                user = user,
                workout = workout,
            )
        } else {
            CustomWorkoutCardClosed(
                workoutCardName = workoutCardName,
                user = user,
                workout = workout,
            )
        }
    }
}

@Composable
fun CustomWorkoutCardClosed(
    workoutCardName: String,
    user: UserProfileUIModel,
    workout: WorkoutUIModel
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
    ) {
        CustomUserImage(user = user)
        Column(
            modifier = Modifier
                .padding(end = 16.dp)
        ) {
            CardTitle(cardName = workoutCardName)
            CardSubTitle(cardSubTitle = workout.type)
        }
        WorkoutMusclesImage(workout = workout, Modifier.weight(1f))
    }
}

@Composable
fun CustomWorkoutCardOpened(
    workoutCardName: String,
    user: UserProfileUIModel,
    workout: WorkoutUIModel
) {
    var openDialog by remember { mutableStateOf(false) }
    var favoriteActivity by remember { mutableStateOf(true) }
    var showActivity by remember { mutableStateOf(true) }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            CustomUserImage(user = user)
            Column(
                modifier = Modifier
                    .padding(end = 16.dp)
            ) {
                CardTitle(cardName = workoutCardName)
                CardSubTitle(cardSubTitle = workout.type)
            }
            Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = { openDialog = true },
                ) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "Show ${workout.name} options"
                    )
                }
            }
        }
        WorkoutMusclesImage(workout = workout, modifier = Modifier.fillMaxWidth())
        Column(Modifier.padding(8.dp)) {
            CardContentTitle(cardContentTitle = workout.name)
            CardContentSubTitle(cardContentSubTitle = workout.muscle)
            CustomExpandableText(text = workout.instructions)
        }
    }

    if (openDialog) {
        Dialog(
            onDismissRequest = { openDialog = false })
        {

            Card(shape = RoundedCornerShape(CornerSize(12))) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = workoutCardName,
                        style = MaterialTheme.typography.h4
                    )
                    Divider()
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Favorite",
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier.padding(8.dp)
                        )
                        Spacer(Modifier.weight(4f))
                        Checkbox(
                            checked = favoriteActivity,
                            onCheckedChange = { favoriteActivity = !favoriteActivity })
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Show",
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier.padding(8.dp)
                        )
                        Spacer(Modifier.weight(4f))
                        Checkbox(
                            checked = showActivity,
                            onCheckedChange = { showActivity = !showActivity })
                    }
                }
            }
        }
    }
}

@Preview("PreviewCustomWorkoutCard, opened and closed", showBackground = true)
@Composable
fun PreviewCustomActivityCard() {
    val workout = WorkoutUIModel(
        name = "Incline Hammer Curls",
        type = "Strength",
        muscle = "Biceps",
        equipment = "dumbbell",
        difficulty = "beginner",
        instructions =
        "Seat yourself on an incline bench with a dumbbell in each hand. You should pressed firmly against he back with your feet together. Allow the dumbbells to hang straight down at your side, holding them with a neutral grip. This will be your starting position. Initiate the movement by flexing at the elbow, attempting to keep the upper arm stationary. Continue to the top of the movement and pause, then slowly return to the start position.\n",
        image = com.example.k_fit.R.drawable.example
    )

    val user = UserProfileUIModel(
        email = "",
        nickName = "Example User",
        firstName = "",
        lastName = "",
        birthDate = "",
        gender = Gender.Other,
        weight = 0,
        height = 0,
        profilePicture = Icons.Filled.AccountCircle
    )
    Column()
    {
        CustomWorkoutCard(
            workoutCardName = "Today's workout",
            user = user,
            workout = workout
        )
        Spacer(modifier = Modifier.padding(8.dp))
        CustomWorkoutCardOpened(
            workoutCardName = "Today's workout",
            user = user,
            workout = workout
        )
        Spacer(modifier = Modifier.padding(8.dp))
        CustomWorkoutCardClosed(
            workoutCardName = "Today's workout",
            user = user,
            workout = workout
        )
    }
}