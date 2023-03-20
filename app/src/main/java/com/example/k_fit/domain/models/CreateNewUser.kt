package com.example.k_fit.domain.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.k_fit.presentation.common.Gender

data class CreateNewUser(
    var email: String,
    var nickName: String,
    var firstName: String,
    var lastName: String,
    var birthDate: String,
    var gender: Gender,
    var weight: Float,
    var height: Float,
    var profilePicture: ImageVector = Icons.Filled.AccountCircle
)
