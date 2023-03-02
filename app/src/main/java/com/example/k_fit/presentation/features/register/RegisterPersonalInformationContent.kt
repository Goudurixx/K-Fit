package com.example.k_fit.presentation.features.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Today
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.k_fit.presentation.components.CustomInputTextComponent

import com.example.k_fit.presentation.components.CustomSegmentedButtonComponent
import com.example.k_fit.ui.theme.HintTextColor

@Composable
fun RegisterPersonalInformation(
    viewModel: RegisterViewModel
) {
    val registerState by viewModel.registerProfileState.collectAsState()
    val context = LocalContext.current
    println(registerState.isScreenValid.toString() + "AAA")
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
        OutlinedTextField(enabled = false,
            label = { Text(text = "Birthdate") },
            value = registerState.birthDate,
            placeholder = { Text(text = "Birthdate") },
            onValueChange = {},
            textStyle = TextStyle(
                fontSize = 20.sp, color = HintTextColor
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clickable(interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { viewModel.showDatePickerDialog(context) }),
            readOnly = false,
            singleLine = true,
            trailingIcon = {
                IconButton(
                    onClick = { println(registerState) },
                ) {
                    Icon(imageVector = Icons.Filled.Today, "Clean field")
                }
            })
        CustomSegmentedButtonComponent(onOptionSelected = { viewModel.updateGender(it) })
    }
}