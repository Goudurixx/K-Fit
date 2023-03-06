package com.example.k_fit.presentation.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.k_fit.presentation.common.Gender

data class UserProfileUIModel(
    var email: String,
    var nickName: String,
    var lastName: String,
    var birthDate: String,
    var gender: Gender,
    var weight: Int,
    var height: Int,
    var profilePicture: ImageVector =  Icons.Filled.AccountCircle
)

