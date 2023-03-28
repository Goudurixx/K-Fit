package com.example.k_fit.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.k_fit.R
import com.example.k_fit.presentation.features.models.WorkoutUIModel
import com.example.k_fit.ui.theme.onSecondaryContainer

@Composable
fun CustomSearchBar(
    modifier: Modifier
) {
    var query by remember { mutableStateOf("") }
    var focusedOn by remember { mutableStateOf(true) }
    val focusRequester = remember { FocusRequester() }

    //TODO move local data from here and get from dataSource
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
        color = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onSurface,
        shape = shape,
        modifier = modifier
            .zIndex(1f)
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .focusRequester(focusRequester)
            .onFocusChanged {
                focusedOn = !focusedOn
            }
    )
    {
        Column(modifier = modifier.background(MaterialTheme.colors.surface.copy(alpha = 0.89f))) {
            Row(
                modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .height(20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                BasicTextField(
                    value = query,
                    onValueChange = { query = it },
                    modifier = modifier
                        .fillMaxWidth(0.9F)
                        .semantics {
                            contentDescription = query
                            onClick {
                                focusRequester.requestFocus()
                                true
                            }
                        },
                    enabled = true,
                    textStyle = TextStyle(color = MaterialTheme.colors.onSecondaryContainer),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)

                )
                if (!focusedOn or query.isEmpty())
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
                if (focusedOn && query.isNotBlank())
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
                LazyColumn(
                    modifier = modifier
                        .height((filteredList.size * 70 + 40).dp)
                        .padding(bottom = 16.dp)
                ) {
                    items(filteredList) { workout ->
                        CustomWorkoutCard(
                            workoutCardName = workout.name,
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