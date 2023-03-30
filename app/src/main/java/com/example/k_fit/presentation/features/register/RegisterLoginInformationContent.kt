package com.example.k_fit.presentation.features.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.k_fit.R
import com.example.k_fit.presentation.components.CustomErrorMessageComponent
import com.example.k_fit.presentation.components.CustomInputPasswordComponent
import com.example.k_fit.presentation.components.CustomInputTextComponent

@Composable
fun RegisterLoginInformationContent(
    viewModel: RegisterViewModel
) {
    val registerState by viewModel.registerProfileState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center, modifier = Modifier.padding(
            all = 16.dp
        )
    ) {
        CustomInputTextComponent(
            title = "E-mail",
            inputText = registerState.email,
            onValueChange = { viewModel.updateEmail(it) },
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
        if (!registerState.isEmailValid) {
          CustomErrorMessageComponent(errorMessage = R.string.wrong_email)
        }
        CustomInputPasswordComponent(
            title = "Password",
            inputPassword = registerState.password ,
            onValueChange = { viewModel.updatePassword(it) },
            imeAction = ImeAction.Next
        )
        if (!registerState.isPasswordValid) {
            CustomErrorMessageComponent(errorMessage = R.string.wrong_password)
        }
        CustomInputPasswordComponent(title = "Confirm Password",
            inputPassword = registerState.passwordConfirm,
            onValueChange = { viewModel.updatePasswordConfirmation(it) },
            imeAction = ImeAction.Done,
            keyboardActions = {
                viewModel.isFormValid()
                if (viewModel.registerProfileState.value.isScreenValid) {
                    viewModel.updateScreenStep(registerState.screenStep + 1)
                }
            })
        if (registerState.isPasswordDifferent) {
           CustomErrorMessageComponent(errorMessage = R.string.different_password)
        }
    }
}