package com.example.k_fit.presentation.features.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.k_fit.R
import com.example.k_fit.presentation.components.CustomInputPasswordComponent
import com.example.k_fit.presentation.components.CustomInputTextComponent

@Composable
fun RegisterLoginInformationContent(
    viewModel: RegisterViewModel
) {
    val registerState by viewModel.registerProfileState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(
                all = 16.dp
            )
    ) {
        CustomInputTextComponent(
            title = "E-mail",
            inputText = registerState.email,
            onValueChange = { viewModel.updateEmail(it) },
            keyboardType = KeyboardType.Email
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
            keyboardActions = { viewModel.updateScreenStep(registerState.screenStep + 1) }
        )
        if (registerState.isPasswordDifferent) {
            Text(
                text = stringResource(R.string.different_password),
                Modifier.absoluteOffset(x = 16.dp),
                color = Color.Red
            )
        }
    }
}