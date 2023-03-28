package com.example.k_fit.presentation.features.loginOrRegister

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.k_fit.presentation.components.CustomButtonComponent

@Composable
fun LoginOrRegisterScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToRegister: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.fillMaxHeight()
            .background(color = MaterialTheme.colors.background)
    ) {
        Row(
            Modifier
                .padding(bottom = 183.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                50.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            CustomButtonComponent(title = "Login", onNavigateToLogin)
            CustomButtonComponent(title = "Register", onNavigateToRegister)
        }
    }
}

@Preview("Preview of the login or register screen", showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginScreen() {
    LoginOrRegisterScreen({}, {})
}