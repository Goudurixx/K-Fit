package com.example.k_fit.domain.repositories

import com.example.k_fit.domain.models.CreateNewUser

interface IFirebaseRepository {
    fun register(createNewUser: CreateNewUser, password: String)
    fun login(email: String, password: String)
}