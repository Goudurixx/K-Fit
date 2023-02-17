package com.example.k_fit.presentation.features.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.k_fit.presentation.components.CustomButtonComponent
import com.example.k_fit.presentation.components.CustomInputPasswordComponent
import com.example.k_fit.presentation.components.CustomInputTextComponent
import com.example.k_fit.presentation.features.register.RegisterScreen
import com.example.k_fit.presentation.features.register.RegisterViewModel

@Composable
fun LoginScreen(onNavigateToFriends: () -> Unit) {
    val viewModel = hiltViewModel<RegisterViewModel>()
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState("")
    val onValueChange: (String) -> Unit = { s: String -> println(s) }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxHeight()
            .padding(
                all = 16.dp

            )
    ) {
        CustomInputTextComponent(
            title = "E-mail",
            hint = "email@email.email",
            inputText = email,
            onValueChange = onValueChange,
            description = "Enter your email here"
        )
        CustomInputPasswordComponent(
            title = "Password",
            hint = "••••••••",
            inputText = password
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    all = 16.dp

                )

        ) {
            CustomButtonComponent(
                title = "login", onNavigateToFriends
            )
        }
    }

}

@Preview("Login screen preview", showSystemUi = true)
@Composable
fun PreviewRegisterScreen() {
    LoginScreen({})
}