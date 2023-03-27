package com.example.k_fit.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.k_fit.ui.theme.md_theme_light_outline

@Composable
fun CustomInputPasswordComponent(
    title: String,
    inputPassword: String,
    imeAction: ImeAction,
    keyboardActions: (KeyboardActionScope.() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(label = { Text(text = title) },
        value = inputPassword,
        placeholder = { Text("Password") },
        onValueChange = onValueChange,
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password, imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onDone = keyboardActions
        ),
        textStyle = TextStyle(
            fontSize = 20.sp, color = md_theme_light_outline
        ),
        modifier = modifier.fillMaxWidth(),
        readOnly = false,
        trailingIcon = {
            val image = if (passwordVisible) Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)
            }
        })
}

@Preview("Preview CustomButtomComponent", showBackground = true)
@Composable
fun PreviewCustomPasswordTextComponent() {
    val inputText = ""
    CustomInputPasswordComponent("Title of the input",
        inputPassword = inputText,
        imeAction = ImeAction.Go,
        onValueChange = {})
}