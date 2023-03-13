package com.example.k_fit.presentation.features.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.k_fit.presentation.components.CustomButtonComponent
import com.example.k_fit.presentation.components.CustomInputPasswordComponent
import com.example.k_fit.presentation.components.CustomInputTextComponent
import com.example.k_fit.presentation.features.register.RegisterScreen
import com.example.k_fit.presentation.features.register.RegisterViewModel

@Composable
fun LoginScreen(onNavigateToFriends: () -> Unit, viewModel: LoginViewModel = hiltViewModel()) {
    val loginState by viewModel.loginState.collectAsState()
    val focusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
            .padding(horizontal = 16.dp)
    ) {
        CustomInputTextComponent(title = "Email", onValueChange = { viewModel.updateEmail(it) }, inputText = loginState.email)
        CustomInputPasswordComponent(
            title = "Password",
            inputPassword = loginState.password,
            imeAction = ImeAction.Done,
            onValueChange = {viewModel.updatePassword(it)})
        CustomButtonComponent(title = "Login") {
            viewModel.login()
        }
    }
}

@Preview("Login screen preview", showSystemUi = true)
@Composable
fun PreviewRegisterScreen() {
    LoginScreen({})
}