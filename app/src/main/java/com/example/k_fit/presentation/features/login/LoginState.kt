package com.example.k_fit.presentation.features.login

data class LoginState(
    var email: String = "",
    var password: String = "",
    var name: String = "",
    var errorMessage: Boolean = false
)