package com.example.k_fit.presentation.features.cardList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.k_fit.presentation.components.CustomWorkoutCard
import com.example.k_fit.presentation.models.CardEntryList
import com.example.k_fit.presentation.models.WorkoutUIModel

@Composable
fun CardListScreen(
    onNavigateToFriends: () -> Unit
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            SearchBar(
                hint = "search things",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            )
        }

    }
}


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }

    var isHintDisplayed by remember {
        mutableStateOf(hint != " ")
    }

    Box(
        modifier = modifier,
    ) {

        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 5.dp, shape = CircleShape)
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }

}

@Composable
fun ListEntry(
    entry: CardEntryList,
    modifier: Modifier = Modifier,
    viewModel: CardListViewModel = hiltViewModel(),
) {
    CustomWorkoutCard(
        workoutCardName = entry.cardTitle,
        cardIcon = ImageVector.vectorResource(entry.workoutDifficulty),
        workout = WorkoutUIModel(
            name = entry.cardTitle,
            type = entry.cardSubtitle,
            muscle = "",
            equipment = "",
            difficulty = "",
            instructions = ""
        )
    )
}

@Composable
@Preview("list of card", showSystemUi = true)
fun PreviewCardListScreen() {
    CardListScreen {

    }
}