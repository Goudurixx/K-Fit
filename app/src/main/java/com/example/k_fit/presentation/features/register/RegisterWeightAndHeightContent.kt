package com.example.k_fit.presentation.features.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.k_fit.ScreenRoute
import com.example.k_fit.presentation.components.CustomInputTextComponent

@Composable
fun RegisterWeightAndHeightContent(
    navHostController: NavHostController,
    viewModel: RegisterViewModel
) {
    val registerState by viewModel.registerProfileState.collectAsState()
    var weight by remember {
        mutableStateOf(registerState.weight.toString())
    }
    var height by remember {
        mutableStateOf(registerState.height.toString())
    }
    val pattern = remember { Regex("^\\d{0,3}(\\.\\d{0,2})?\$") }

    fun formatText(text: String): String {
        if (text.toFloatOrNull() != 0f)
            return text
        return ""
    }

    Column(
        verticalArrangement = Arrangement.Center, modifier = Modifier.padding(
            all = 16.dp
        )
    ) {
        CustomInputTextComponent(
            title = "Weight",
            inputText = formatText(weight),
            onValueChange = {
                if (it.matches(pattern)) {
                    weight = it
                    viewModel.updateWeight(weight)
                }
            },
            trailingIcon = { Text(text = "kg") },
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        )
        CustomInputTextComponent(
            title = "Height",
            inputText = formatText(height),
            onValueChange = {
                if (it.matches(pattern)) {
                    height = it
                    viewModel.updateHeight(height)
                }
            },
            trailingIcon = { Text(text = "cm") },
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
            keyboardActions = {
                viewModel.isFormValid()
                if (viewModel.registerProfileState.value.isScreenValid) {
                    viewModel.registerUser { navHostController.navigate(ScreenRoute.LoginOrRegister.route) }
                }
            }
        )
    }
}