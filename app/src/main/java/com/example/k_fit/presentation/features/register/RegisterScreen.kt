package com.example.k_fit.presentation.features.register

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.k_fit.R
import com.example.k_fit.presentation.components.CustomRedirectionButton

@Composable
fun RegisterScreen(
    onNavigateToFriends: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val registerState by viewModel.registerProfileState.collectAsState()
    val focusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
    ) {
        when (registerState.screenStep) {
            1f -> RegisterLoginInformationContent(viewModel)
            2f -> RegisterPersonalInformation(viewModel)
            3f -> RegisterWeightAndHeightContent(viewModel)
        }
        if (!registerState.isScreenValid)
            Text(
                text = stringResource(R.string.empty_form),
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        Row(
            Modifier.padding(bottom = 64.dp, top = 64.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            if (registerState.screenStep == 2f || registerState.screenStep == 3f) {
                CustomRedirectionButton({
                    viewModel.updateScreenStep(registerState.screenStep - 1)
                }, Icons.Filled.ArrowBack, "Go Back")
                Spacer(modifier = Modifier.width(20.dp))
                if (registerState.screenStep == 3f)
                    CustomRedirectionButton({
                        viewModel.registerUser(onNavigateToFriends)
                    }, Icons.Filled.Check, "Validate")
            }
            if (registerState.screenStep != 3f)
                CustomRedirectionButton({
                    viewModel.isFormValid()
                    if (viewModel.registerProfileState.value.isScreenValid) {
                        viewModel.updateScreenStep(registerState.screenStep + 1)
                    }
                }, Icons.Filled.ArrowForward, "Go Forward")
        }
        LinearProgressIndicator(
            progress = ((registerState.screenStep * 0.333) % 1).toFloat(),
            modifier = Modifier.padding(bottom = 64.dp)
        )
    }
}
