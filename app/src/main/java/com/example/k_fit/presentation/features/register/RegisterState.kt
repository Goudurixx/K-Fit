package com.example.k_fit.presentation.features.register

data class RegisterState(
    var email: String = "",
    var password: String = "",
    var passwordConfirm: String ="",
    var isEmailValid: Boolean = true,
    var isPasswordValid: Boolean = true,
    var isPasswordDifferent: Boolean = false
    )