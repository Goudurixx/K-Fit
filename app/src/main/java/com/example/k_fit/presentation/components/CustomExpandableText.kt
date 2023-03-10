package com.example.k_fit.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomExpandableText(
    text: String,
    minimizedMaxLines: Int = 3
) {
    var isExpanded by remember { mutableStateOf(false) }

    SelectionContainer{
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = text,
                maxLines = if (isExpanded) Int.MAX_VALUE else minimizedMaxLines,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) { isExpanded = !isExpanded }
                    .animateContentSize(),
            )
        }
    }
}

@Preview("text expandable preview", showBackground = true)
@Composable
fun PreviewExtendedText() {
    CustomExpandableText(
        text = "Seat yourself on an incline bench with a dumbbell in each hand\n You should pressed firmly against he back with your feet together. \n" +
                " Allow the dumbbells to hang straight down at your side, holding them with a neutral grip. This will be your starting position. Initiate the movement by flexing at the elbow,\n" +
                "  attempting to keep the upper arm stationary. Continue to the top of the movement and pause, then slowly return to the start position.",
    )
}