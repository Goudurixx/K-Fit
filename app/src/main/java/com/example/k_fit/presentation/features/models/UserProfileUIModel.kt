package com.example.k_fit.presentation.features.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.k_fit.presentation.common.Gender

data class UserProfileUIModel(
    var email: String,
    var nickName: String,
    var firstName: String,
    var lastName: String,
    var birthDate: String,
    var gender: Gender,
    var weight: Int,
    var height: Int,
    var profilePicture: ImageVector =  Icons.Filled.AccountCircle
)

