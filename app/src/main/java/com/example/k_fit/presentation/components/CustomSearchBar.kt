package com.example.k_fit.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.k_fit.R
import com.example.k_fit.presentation.common.Gender
import com.example.k_fit.presentation.features.models.UserProfileUIModel
import com.example.k_fit.presentation.features.models.WorkoutUIModel

@Composable
fun CustomSearchBar(
    modifier: Modifier
) {
    var query by remember { mutableStateOf("") }

    var focusedOn by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

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
    val shape = GenericShape()
    { size, _ ->
        val radius = 60f
        addRoundRect(RoundRect(size.toRect(), CornerRadius(radius)))
    }
    val filteredList = filterList(query, workoutList)
    Surface(
        color = Color(R.color.surface3).copy(alpha = 0.11f),
        shape = shape,
        modifier = modifier
            .zIndex(1f)
            .background(
                shape = shape,
                color = Color.White.copy(alpha = 1f)
            )
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .focusRequester(focusRequester)
            .onFocusChanged {
                if (it.isFocused) focusedOn = true
                if (!it.isFocused) focusedOn = false
            }
    )
    {
        Column {
            Row(
                modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .height(20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                BasicTextField(
                    value = query,
                    onValueChange = { query = it },
                    enabled = true,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    modifier = modifier
                        .fillMaxWidth(0.9F)
                        .semantics {
                            contentDescription = query
                            onClick {
                                focusRequester.requestFocus()
                                true
                            }
                        },
                )
                if (!focusedOn)
                    IconButton(
                        onClick = {
                            query = ""
                            focusRequester.requestFocus()
                        },
                    ) {
                        Icon(
                            Icons.Filled.Search,
                            "search workout",
                        )
                    }
                if (focusedOn)
                    IconButton(
                        onClick = { query = "" },

                        ) {
                        Icon(
                            Icons.Filled.Close,
                            "clear query",
                        )
                    }
            }
            if (focusedOn) {
                Divider()
                LazyColumn(modifier = modifier.height((filteredList.size * 70 + 40).dp)) {
                    items(filteredList) { workout ->
                        CustomWorkoutCard(
                            workoutCardName = workout.name,
                            user = UserProfileUIModel(
                                email = "",
                                nickName = "",
                                firstName = "",
                                lastName = "",
                                birthDate = "",
                                gender = Gender.Other,
                                weight = 0,
                                height = 0,
                                profilePicture = Icons.Filled.BrokenImage
                            ),
                            workout = workout
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun filterList(searchText: String, list: List<WorkoutUIModel>): List<WorkoutUIModel> {
    return list.filter {
        searchText.isNotBlank() && it.name.contains(searchText, ignoreCase = true)
    }
}

@Preview("preview of the search bar on some screen with dummies", showSystemUi = true)
@Composable
fun PreviewSearchBar() {
    CustomSearchBar(Modifier)
}