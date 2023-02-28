package com.example.k_fit.presentation.features.register

import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.k_fit.R
import com.example.k_fit.presentation.components.CustomBackwardRedirectionButton
import com.example.k_fit.presentation.components.CustomForwardRedirectionButton
import com.example.k_fit.presentation.components.CustomInputPasswordComponent
import com.example.k_fit.presentation.components.CustomInputTextComponent

@Composable
fun RegisterScreen(
    onNavigateToFriends: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val registerState by viewModel.registerState.collectAsState()
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
    ) {
        when (registerState.screenStep) {
            1f -> RegisterLoginInformationContent(viewModel)
            2f -> RegisterPersonalInformation(viewModel)
        }
        Row(Modifier.padding(bottom = 64.dp, top = 64.dp)) {
            if (registerState.screenStep == 2f)
                CustomBackwardRedirectionButton {
                    viewModel.updateScreenStep(registerState.screenStep - 1)
                }
            CustomForwardRedirectionButton {
                viewModel.updateScreenStep(registerState.screenStep + 1)
            }
        }
        LinearProgressIndicator(
            progress = ((registerState.screenStep * 0.333) % 1).toFloat(),
            modifier = Modifier.padding(bottom = 64.dp)
        )
    }
}

//
//    @Preview("Register screen preview", showSystemUi = true)
//    @Composable
//    fun PreviewRegisterScreen() {
//        RegisterScreen({})
//    }

