package com.example.k_fit.presentation.features.register

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.k_fit.R
import com.example.k_fit.presentation.components.CustomButtonComponent
import com.example.k_fit.presentation.components.CustomInputPasswordComponent
import com.example.k_fit.presentation.components.CustomInputTextComponent

@Composable
fun RegisterScreen(onNavigateToFriends: () -> Unit) {
    val viewModel = hiltViewModel<RegisterViewModel>()

    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val passwordConfirm by viewModel.passwordConfirmation.collectAsState()
    val isPasswordDifferent by viewModel.isPasswordDifferent.collectAsState()

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
            onValueChange = { viewModel.updateEmail(it) },
            description = "Enter your email here",

            )
        CustomInputPasswordComponent(
            title = "Password",
            inputPassword = password,
            onValueChange = { viewModel.updatePassword(it) }
        )
        CustomInputPasswordComponent(
            title = "Confirm Password",
            inputPassword = passwordConfirm,
            onValueChange = { viewModel.updatePasswordConfirmation(it) }
        )
        if (isPasswordDifferent)
            Text(text = stringResource(R.string.different_password))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    all = 16.dp
                )

        ) {
            CustomButtonComponent(
                title = "Register",
                onClick = { viewModel.registerUser(home = onNavigateToFriends) },
            )
        }
    }

}


@Preview("Register screen preview", showSystemUi = true)
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen({})
}