package com.example.k_fit.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Today
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.k_fit.ui.theme.HintTextColor

@Composable
fun CustomDatePickerComponent(
    pickedDate: String,
    onClick: () -> Unit
) {
    OutlinedTextField(enabled = false,
        label = { Text(text = "Birthdate") },
        value = pickedDate,
        placeholder = { Text(text = "Birthdate") },
        onValueChange = {},
        textStyle = TextStyle(
            fontSize = 20.sp, color = HintTextColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onClick() }),
        readOnly = false,
        singleLine = true,
        trailingIcon = {
            IconButton(
                onClick = { },
            ) {
                Icon(imageVector = Icons.Filled.Today, "Clean field")
            }
        })
}