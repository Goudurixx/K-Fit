package com.example.k_fit.presentation.features.loginOrRegister

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment

@Composable
fun LoginOrRegisterScreen(
    onNavigateToFriends: () -> Unit,
) {
    val viewModel by remember { mutableStateOf(LoginOrRegisterViewModel()) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "LoginOrRegisterScreen")
    }
}

