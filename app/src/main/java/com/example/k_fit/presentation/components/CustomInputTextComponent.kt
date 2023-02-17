package com.example.k_fit.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.k_fit.ui.theme.*

@Composable
fun CustomInputTextComponent(
    title: String,
    hint: String,
    description: String,
    onValueChange: (String) -> Unit,
    inputText: String,
    modifier: Modifier = Modifier
) {
    var inputText by rememberSaveable { mutableStateOf(inputText) }
    Column {
        OutlinedTextField(
            label = { Text(text = title) },
            value = inputText,
            placeholder = { Text(hint) },
            onValueChange = { inputText = it },
            keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = HintTextColor
            ),
            modifier = modifier.fillMaxWidth(),
            readOnly = false
        )
        Text(
            text = description,
            Modifier.absoluteOffset(x = 16.dp)
        )
    }
}

@Preview("Preview CustomButtomComponent", showBackground = true, showSystemUi = true)
@Composable
fun PreviewCustomInputTextComponent() {
    val inputText: String = ""
    val onValueChange: (String) -> Unit = { s: String -> println(s) }
    CustomInputTextComponent(
        "Title of the input",
        hint = "Hint for the input",
        description = "Enter the description of what to enter here, leave blank for nothing",
        onValueChange = onValueChange,
        inputText = inputText,
    )
}