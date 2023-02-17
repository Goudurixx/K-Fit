package com.example.k_fit.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.k_fit.ui.theme.*

@Composable
fun CustomInputPasswordComponent(
    title: String,
    hint: String,
    inputText: String,
    modifier: Modifier = Modifier
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var password by rememberSaveable { mutableStateOf(inputText) }
    OutlinedTextField(
        label = { Text(text = title) },
        value = password,
        placeholder = { Text(hint) },
        onValueChange = { password = it },
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
        readOnly = false,
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            // Please provide localized description for accessibility services
            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = {passwordVisible = !passwordVisible}){
                Icon(imageVector  = image, description)
            }
        }
    )
}

@Preview("Preview CustomButtomComponent", showBackground = true)
@Composable
fun PreviewCustomPasswordTextComponent() {
    val inputText: String = ""
    CustomInputPasswordComponent(
        "Title of the input",
        hint = "Hint for the input",
        inputText = inputText,
    )
}