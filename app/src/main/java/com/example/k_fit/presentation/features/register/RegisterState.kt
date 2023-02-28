package com.example.k_fit.presentation.features.register

import java.util.Date

data class RegisterState(
    var screenStep: Float = 1.0f,
    var email: String = "",
    var password: String = "",
    var passwordConfirm: String ="",
    var isEmailValid: Boolean = true,
    var isPasswordValid: Boolean = true,
    var isPasswordDifferent: Boolean = false,
    var nickName: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var birthDate: Date? = null
)