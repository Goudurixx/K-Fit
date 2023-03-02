package com.example.k_fit.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.k_fit.presentation.common.Gender
import com.example.k_fit.ui.theme.*

@Composable
fun CustomSegmentedButtonComponent(
    defaultSelectedItemIndex: Int = 0,
    onOptionSelected: (Gender) -> Unit
) {
    val selectedIndex = remember { mutableStateOf(defaultSelectedItemIndex) }
    val options: List<Gender> = listOf(Gender.Female, Gender.Male, Gender.Other)
    Row {
        options.forEachIndexed { index, option ->
            OutlinedButton(
                modifier = Modifier
                    .height(48.dp)
                    .offset(y = 20.dp),
                shape = when (index) {
                    0 -> RoundedCornerShape(
                        topStartPercent = 100,
                        topEndPercent = 0,
                        bottomStartPercent = 100,
                        bottomEndPercent = 0
                    )
                    options.size - 1 -> RoundedCornerShape(
                        topStartPercent = 0,
                        topEndPercent = 100,
                        bottomStartPercent = 0,
                        bottomEndPercent = 100
                    )
                    else -> RoundedCornerShape(
                        topStartPercent = 0,
                        topEndPercent = 0,
                        bottomStartPercent = 0,
                        bottomEndPercent = 0
                    )
                },
                border = BorderStroke(1.dp, HintTextColor),
                onClick = {
                    selectedIndex.value = index
                    onOptionSelected(option)
                },
                colors = if (selectedIndex.value == index) {
                    ButtonDefaults.buttonColors(
                        backgroundColor = LightPurple
                    )
                } else {
                    ButtonDefaults.buttonColors(backgroundColor = Color.White)
                }
            )
            {
                Text(text = option.toString())
            }
        }
    }
}


@Preview
@Composable
fun PreviewCustomSegmentedButtonComponent() {
    val options: List<Gender> = listOf(Gender.Female, Gender.Male, Gender.Other)
    CustomSegmentedButtonComponent(
        onOptionSelected = {}
    )
}