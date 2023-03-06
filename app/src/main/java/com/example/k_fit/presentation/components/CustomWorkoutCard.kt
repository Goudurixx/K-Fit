package com.example.k_fit.presentation.components


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.k_fit.presentation.common.Gender
import com.example.k_fit.presentation.models.UserProfileUIModel
import com.example.k_fit.presentation.models.WorkoutUIModel
import com.example.k_fit.ui.theme.CardBackground
import com.example.k_fit.ui.theme.CardStroke

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
    lastName = "",
    birthDate = "",
    gender = Gender.Other,
    weight = 0,
    height = 0,
    profilePicture = Icons.Filled.AccountCircle
)

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

        UserImage(user = user)
        Column(
            modifier = Modifier
                .padding(end = 16.dp)
        ) {
            WorkoutTitle(workoutCardName = workoutCardName)
            WorkoutMuscle(workoutMuscle = workout.type)
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

            UserImage(user = user)
            Column(
                modifier = Modifier
                    .padding(end = 16.dp)
            ) {
                WorkoutTitle(workoutCardName = workoutCardName)
                WorkoutMuscle(workoutMuscle = workout.type)
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
            WorkoutTitle(workoutCardName = workout.name)
            WorkoutMuscle(workoutMuscle = workout.muscle)
            ExpandableText(text = workout.instructions)
        }
    }
    //TODO way to update the state of checkbox outside of the scope of this card
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

//TODO Move outside
@Composable
fun UserImage(user: UserProfileUIModel) {
    val rainbowColorsBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFF9575CD),
                Color(0xFFBA68C8),
                Color(0xFFE57373),
                Color(0xFFFFB74D),
                Color(0xFFFFF176),
                Color(0xFFAED581),
                Color(0xFF4DD0E1),
                Color(0xFF9575CD)
            )
        )
    }
    Image(
        imageVector = user.profilePicture,
        contentDescription = "Profile picture of ${user.nickName}",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(16.dp)
            .size(40.dp)
            .clip(CircleShape)
            .border(
                BorderStroke(1.dp, rainbowColorsBrush),
                CircleShape
            )
    )
}

@Composable
fun WorkoutTitle(workoutCardName: String) {
    Text(
        text = workoutCardName,
        style = MaterialTheme.typography.subtitle1,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun WorkoutMuscle(workoutMuscle: String) {
    Text(
        text = workoutMuscle,
        style = MaterialTheme.typography.body2,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
    )
}


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


@Preview("PreviewCustomWorkoutCard Closed", showBackground = true)
@Composable
fun PreviewCustomWorkoutCardClosed() {

    CustomWorkoutCardClosed(
        workoutCardName = "Today's workout",
        user = user,
        workout = workout
    )
}

@Preview("PreviewCustomWorkoutCard Opened", showBackground = true)
@Composable
fun PreviewCustomWorkoutCardOpened() {

    CustomWorkoutCardOpened(
        workoutCardName = "Today's workout",
        user = user,
        workout = workout
    )
}

@Preview("PreviewCustomWorkoutCard", showBackground = true)
@Composable
fun PreviewCustomActivityCard() {

    CustomWorkoutCard(
        workoutCardName = "Today's workout",
        user = user,
        workout = workout
    )
}


//TODO Move outside
@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    minimizedMaxLines: Int = 3,
) {
    var cutText by remember(text) { mutableStateOf<String?>(null) }
    var expanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    val seeMoreSizeState = remember { mutableStateOf<IntSize?>(null) }
    val seeMoreOffsetState = remember { mutableStateOf<Offset?>(null) }

    val textLayoutResult = textLayoutResultState.value
    val seeMoreSize = seeMoreSizeState.value
    val seeMoreOffset = seeMoreOffsetState.value

    LaunchedEffect(text, expanded, textLayoutResult, seeMoreSize) {
        val lastLineIndex = minimizedMaxLines - 1
        if (!expanded && textLayoutResult != null && seeMoreSize != null
            && lastLineIndex + 1 == textLayoutResult.lineCount
            && textLayoutResult.isLineEllipsized(lastLineIndex)
        ) {
            var lastCharIndex = textLayoutResult.getLineEnd(lastLineIndex, visibleEnd = true) + 1
            var charRect: Rect
            do {
                lastCharIndex -= 1
                charRect = textLayoutResult.getCursorRect(lastCharIndex)
            } while (
                charRect.left > textLayoutResult.size.width - seeMoreSize.width
            )
            seeMoreOffsetState.value = Offset(charRect.left, charRect.bottom - seeMoreSize.height)
            cutText = text.substring(startIndex = 0, endIndex = lastCharIndex)
        }
    }

    Box(modifier) {
        Text(
            text = cutText ?: text,
            maxLines = if (expanded) Int.MAX_VALUE else minimizedMaxLines,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResultState.value = it },
        )
        if (!expanded) {
            val density = LocalDensity.current
            Text(
                "... See more",
                onTextLayout = { seeMoreSizeState.value = it.size },
                modifier = Modifier
                    .then(
                        if (seeMoreOffset != null)
                            Modifier.offset(
                                x = with(density) { seeMoreOffset.x.toDp() },
                                y = with(density) { seeMoreOffset.y.toDp() },
                            )
                        else
                            Modifier
                    )
                    .clickable {
                        expanded = true
                        cutText = null
                    }
                    .alpha(if (seeMoreOffset != null) 1f else 0f)
            )
        }
    }
}

@Preview("text expandable preview", showBackground = true)
@Composable
fun PreviewExtendedText() {
    ExpandableText(
        text = "Seat yourself on an incline bench with a dumbbell in each hand\n You should pressed firmly against he back with your feet together. \n" +
                " Allow the dumbbells to hang straight down at your side, holding them with a neutral grip. This will be your starting position. Initiate the movement by flexing at the elbow,\n" +
                "  attempting to keep the upper arm stationary. Continue to the top of the movement and pause, then slowly return to the start position.",
    )
}
