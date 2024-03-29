package com.example.k_fit.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun CustomInputTextComponent(
    title: String,
    onValueChange: (String) -> Unit,
    inputText: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    trailingIcon: @Composable() (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction,
    keyboardActions: (KeyboardActionScope.() -> Unit)? = null
) {
    Column {
        OutlinedTextField(
            enabled = enabled,
            label = { Text(text = title) },
            value = inputText,
            placeholder = { Text(title) },
            onValueChange = onValueChange,
            textStyle = TextStyle(
                fontSize = 20.sp, color = MaterialTheme.colors.onSurface
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType, imeAction = imeAction,
            ),
            keyboardActions = KeyboardActions(
                onDone = keyboardActions
            ),
            modifier = modifier.fillMaxWidth(),
            readOnly = false,
            singleLine = true,
            trailingIcon = trailingIcon
        )
    }
}

@Preview("Preview CustomButtomComponent", showBackground = true, showSystemUi = true)
@Composable
fun PreviewCustomInputTextComponent() {
    val inputText = ""
    val onValueChange: (String) -> Unit = { s: String -> println(s) }
    CustomInputTextComponent(
        "Title of the input",
        onValueChange = onValueChange,
        inputText = inputText,
        imeAction = ImeAction.Done
    )
}