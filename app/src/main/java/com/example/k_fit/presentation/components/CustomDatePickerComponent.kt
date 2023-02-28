package com.example.k_fit.presentation.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.k_fit.ui.theme.*


@Composable
fun CustomDatePickerComponent(
    title: String,
    hint: String,
    onValueChange: (String) -> Unit,
    inputText: String,
    modifier: Modifier = Modifier,
) {
    Column {
        OutlinedTextField(
            enabled = false,
            label = { Text(text = title) },
            value = inputText,
            placeholder = { Text(hint) },
            onValueChange = onValueChange,
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = HintTextColor
            ),
            modifier = modifier.fillMaxWidth(),
            readOnly = false,
            singleLine = true,
            trailingIcon = {
                    IconButton(
                        onClick = {},
                    ) {
                        Icon(imageVector = Icons.Filled.Today, "Clean field")
                    }
            }
        )
    }
}

@Preview("Preview CustomButtomComponent", showBackground = true, showSystemUi = true)
@Composable
fun PreviewCustomDatePickerComponent() {
    val inputText = ""
    val onValueChange: (String) -> Unit = { s: String -> println(s) }
    CustomDatePickerComponent(
        "Title of the input",
        hint = "Hint for the input",
        onValueChange = onValueChange,
        inputText = inputText,
    )
}


