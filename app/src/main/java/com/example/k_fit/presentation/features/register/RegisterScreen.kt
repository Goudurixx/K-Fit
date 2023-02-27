package com.example.k_fit.presentation.features.register

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.k_fit.R
import com.example.k_fit.presentation.components.CustomButtonComponent
import com.example.k_fit.presentation.components.CustomInputPasswordComponent
import com.example.k_fit.presentation.components.CustomInputTextComponent

@Composable
fun RegisterScreen(
    onNavigateToFriends: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {

    val registerState by viewModel.registerState.collectAsState()
    val emailDescription = "Enter your email here"
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
            inputText = registerState.email,
            onValueChange = { viewModel.updateEmail(it) },
            description = emailDescription,
            showDescription = registerState.isEmailValid
        )
        if (!registerState.isEmailValid) {
            Text(
                text = stringResource(R.string.wrong_email),
                Modifier.absoluteOffset(x = 16.dp),
                color = Color.Red
            )
        }
        CustomInputPasswordComponent(
            title = "Password",
            inputPassword = registerState.password,
            onValueChange = { viewModel.updatePassword(it) },
            imeAction = ImeAction.Next
        )
        if (!registerState.isPasswordValid) {
            Text(
                text = stringResource(R.string.wrong_password),
                Modifier.absoluteOffset(x = 16.dp),
                color = Color.Red
            )
        }
        CustomInputPasswordComponent(
            title = "Confirm Password",
            inputPassword = registerState.passwordConfirm,
            onValueChange = { viewModel.updatePasswordConfirmation(it) },
            imeAction = ImeAction.Done,
            keyboardActions = { viewModel.registerUser(home = onNavigateToFriends) }
        )
        if (registerState.isPasswordDifferent) {
            Text(
                text = stringResource(R.string.different_password),
                Modifier.absoluteOffset(x = 16.dp),
                color = Color.Red
            )
        }
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