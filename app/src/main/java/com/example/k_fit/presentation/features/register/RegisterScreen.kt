package com.example.k_fit.presentation.features.register

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.k_fit.presentation.components.CustomButtonComponent
import com.example.k_fit.presentation.components.CustomInputPasswordComponent
import com.example.k_fit.presentation.components.CustomInputTextComponent

@Composable
fun RegisterScreen(onNavigateToFriends: () -> Unit) {
    val viewModel = hiltViewModel<RegisterViewModel>()

    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState("")
// val passwordConfirm: MutableState<String> = remember { mutableStateOf("") }
    val loading: Boolean by viewModel.loading.observeAsState(initial = false)
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
            onValueChange = { viewModel.updateEmail(it) },
            description = "Enter your email here",

            )
        CustomInputPasswordComponent(
            title = "Password",
            hint = "••••••••",
            inputText = password
        )
//        CustomInputPasswordComponent(
//            title = "Confirm Password",
//            hint = "••••••••",
//            inputText = passwordConfirm
//        )
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