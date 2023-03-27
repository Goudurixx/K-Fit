package com.example.k_fit.presentation.features.userProfile

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.k_fit.presentation.components.CustomButtonComponent
import com.example.k_fit.presentation.components.CustomInputTextComponent
import com.example.k_fit.presentation.components.CustomRedirectionButton

@Composable
fun UserProfileScreen(goBack: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 64.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomRedirectionButton(
                onClick = goBack,
                imageVector = Icons.Filled.ArrowBack,
                description = "Go back to main page"
            )
            CustomRedirectionButton(
                onClick = {},
                imageVector = Icons.Filled.Logout,
                description = "Logout"
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            CustomInputTextComponent(title = "Nickname", onValueChange = {}, inputText = "Batman")
            CustomInputTextComponent(title = "Firstname", onValueChange = {}, inputText = "Bruce")
            CustomInputTextComponent(title = "Lastname", onValueChange = {}, inputText = "Wayne")
            CustomInputTextComponent(
                title = "Weight",
                inputText = "78",
                onValueChange = {
                },
                trailingIcon = { Text(text = "kg") },
                keyboardType = KeyboardType.Number,
            )
            CustomButtonComponent(title = "Confirm", {})
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewUserProfileScreen() {
    UserProfileScreen({})
}