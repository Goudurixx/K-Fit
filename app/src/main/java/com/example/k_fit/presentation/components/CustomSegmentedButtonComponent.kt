package com.example.k_fit.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.k_fit.presentation.common.Gender
import com.example.k_fit.ui.theme.outline
import com.example.k_fit.ui.theme.primaryContainer

@Composable
fun CustomSegmentedButtonComponent(
    defaultSelectedItemIndex: Int = 0, onOptionSelected: (Gender) -> Unit
) {
    val selectedIndex = remember { mutableStateOf(defaultSelectedItemIndex) }
    val options: List<Gender> = listOf(Gender.Female, Gender.Male, Gender.Other)
    Row {
        options.forEachIndexed { index, option ->
            OutlinedButton(
                modifier = Modifier
                    .height(48.dp)
                    .offset(y = 20.dp), shape = when (index) {
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
                }, border = BorderStroke(1.dp, MaterialTheme.colors.outline), onClick = {
                    selectedIndex.value = index
                    onOptionSelected(option)
                }, colors = if (selectedIndex.value == index) {
                    ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primaryContainer
                    )
                } else {
                    ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface)
                }
            ) {
                Text(text = option.toString(), color = MaterialTheme.colors.onSurface)
            }
        }
    }
}

@Preview
@Composable
fun PreviewCustomSegmentedButtonComponent() {
    val options: List<Gender> = listOf(Gender.Female, Gender.Male, Gender.Other)
    CustomSegmentedButtonComponent(onOptionSelected = {})
}