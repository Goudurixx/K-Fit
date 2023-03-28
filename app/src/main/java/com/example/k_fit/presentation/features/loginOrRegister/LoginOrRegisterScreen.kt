package com.example.k_fit.presentation.features.loginOrRegister

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.k_fit.ScreenRoute
import com.example.k_fit.presentation.components.CustomButtonComponent

@Composable
fun LoginOrRegisterScreen(
    navHostController: NavHostController,
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
            CustomButtonComponent(title = "Login",
                onClick = { navHostController.navigate(ScreenRoute.Login.route) })
            CustomButtonComponent(
                title = "Register",
                onClick = { navHostController.navigate(ScreenRoute.Register.route) })
        }
    }
}

@Preview("Preview of the login or register screen", showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginScreen() {
    val navHostController = rememberNavController()
    LoginOrRegisterScreen(navHostController)
}