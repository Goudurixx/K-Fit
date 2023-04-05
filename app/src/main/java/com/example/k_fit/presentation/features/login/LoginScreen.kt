package com.example.k_fit.presentation.features.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.k_fit.R
import com.example.k_fit.ScreenRoute
import com.example.k_fit.presentation.components.CustomButtonComponent
import com.example.k_fit.presentation.components.CustomInputPasswordComponent
import com.example.k_fit.presentation.components.CustomInputTextComponent

@Composable
fun LoginScreen(
    navHostController: NavHostController, viewModel: LoginViewModel = hiltViewModel()
) {
    val loginState by viewModel.loginState.collectAsState()
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

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
            .background(color = MaterialTheme.colors.background)
            .padding(horizontal = 16.dp, vertical = 183.dp)) {
        CustomInputTextComponent(
            title = "Email",
            onValueChange = { viewModel.updateEmail(it) },
            inputText = loginState.email,
            imeAction = ImeAction.Next
        )
        CustomInputPasswordComponent(
            title = "Password",
            inputPassword = loginState.password,
            imeAction = ImeAction.Done,
            keyboardActions = {
                viewModel.login {
                    navHostController.navigate(ScreenRoute.Login.route)
                }
            },
            onValueChange = { viewModel.updatePassword(it) })
        if (loginState.errorMessage)
            Toast.makeText(context, R.string.wrong_credential, Toast.LENGTH_SHORT).show()
        Spacer(modifier = Modifier.padding(top = 100.dp))
        CustomButtonComponent(title = "Login") {
            viewModel.login {
                navHostController.navigate(ScreenRoute.MainPage.route)
            }
        }
        Text(
            text = "Not yet an user ? REGISTER",
            modifier = Modifier
                .padding(16.dp)
                .clickable(onClick =
                { navHostController.navigate(ScreenRoute.Register.route) }
                )
        )
    }
}

@Preview("Login screen preview", showSystemUi = true)
@Composable
fun PreviewRegisterScreen() {
    val navHostController = rememberNavController()
    LoginScreen(navHostController)
}