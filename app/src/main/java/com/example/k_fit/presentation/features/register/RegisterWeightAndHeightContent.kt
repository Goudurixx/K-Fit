package com.example.k_fit.presentation.features.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.k_fit.presentation.components.CustomInputTextComponent

@Composable
fun RegisterWeightAndHeightContent(
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
            title = "Weight",
            inputText = registerState.weight.toString(),
            onValueChange = { viewModel.updateWeight(it) },
            trailingIcon = { Text(text = "kg") },
            keyboardType = KeyboardType.Number
        )
        CustomInputTextComponent(
            title = "Height",
            inputText = registerState.height.toString(),
            onValueChange = { viewModel.updateHeight(it) },
            trailingIcon = { Text(text = "cm") },
            keyboardType = KeyboardType.Number
        )
    }
}