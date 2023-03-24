package com.example.k_fit.presentation.features.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.k_fit.presentation.components.CustomDatePickerComponent
import com.example.k_fit.presentation.components.CustomInputTextComponent
import com.example.k_fit.presentation.components.CustomSegmentedButtonComponent

@Composable
fun RegisterPersonalInformation(
    viewModel: RegisterViewModel
) {
    val registerState by viewModel.registerProfileState.collectAsState()
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(
            all = 16.dp
        )
    ) {
        CustomInputTextComponent(
            title = "Nickname",
            inputText = registerState.nickName,
            onValueChange = { viewModel.updateNickName(it) },
        )
        CustomInputTextComponent(
            title = "Firstname",
            inputText = registerState.firstName,
            onValueChange = { viewModel.updateFirstName(it) },
        )
        CustomInputTextComponent(
            title = "Lastname",
            inputText = registerState.lastName,
            onValueChange = { viewModel.updateLastName(it) },
        )
        CustomDatePickerComponent(
            pickedDate = registerState.birthDate,
            onClick = { viewModel.showDatePickerDialog(context) })
        CustomSegmentedButtonComponent(onOptionSelected = { viewModel.updateGender(it) })
    }
}