package com.example.k_fit.presentation.features.register

import com.example.k_fit.presentation.common.Gender
data class RegisterProfileState(
    var screenStep: Float = 1.0f,
    var email: String = "",
    var password: String = "",
    var passwordConfirm: String ="",
    var isEmailValid: Boolean = true,
    var isPasswordValid: Boolean = true,
    var isPasswordDifferent: Boolean = false,
    var isScreenValid: Boolean = true,
    var nickName: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var birthDate: String = "",
    var gender: Gender? = null,
    var weight: Int = 0,
    var height: Int = 0,
)