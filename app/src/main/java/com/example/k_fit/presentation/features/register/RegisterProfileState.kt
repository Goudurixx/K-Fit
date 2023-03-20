package com.example.k_fit.presentation.features.register

import com.example.k_fit.presentation.common.Gender

//data class RegisterProfileState(
//    var screenStep: Float = 1.0f,
//    var email: String = "",
//    var password: String = "",
//    var passwordConfirm: String = "",
//    var isEmailValid: Boolean = true,
//    var isPasswordValid: Boolean = true,
//    var isPasswordDifferent: Boolean = false,
//    var isScreenValid: Boolean = true,
//    var nickName: String = "",
//    var firstName: String = "",
//    var lastName: String = "",
//    var birthDate: String = "",
//    var gender: Gender? = null,
//    var weight: Float = 0f,
//    var height: Float = 0f,
//)
data class RegisterProfileState(
    var screenStep: Float = 1.0f,
    var email: String = "ssss@sss.ss",
    var password: String = "Test123#",
    var passwordConfirm: String = "Test123#",
    var isEmailValid: Boolean = true,
    var isPasswordValid: Boolean = true,
    var isPasswordDifferent: Boolean = false,
    var isScreenValid: Boolean = true,
    var nickName: String = "sss",
    var firstName: String = "ssss",
    var lastName: String = "sss",
    var birthDate: String = "ssss",
    var gender: Gender? = Gender.Other,
    var weight: Float = 0f,
    var height: Float = 0f,
)