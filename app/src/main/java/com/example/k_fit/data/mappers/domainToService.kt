package com.example.k_fit.data.mappers

import com.example.k_fit.data.models.NewUser
import com.example.k_fit.domain.models.CreateNewUser

internal fun CreateNewUser.toService(): NewUser = NewUser(
    this.email,
    this.nickName,
    this.firstName,
    this.lastName,
    this.birthDate,
    this.gender,
    this.weight,
    this.height,
    this.profilePicture
)