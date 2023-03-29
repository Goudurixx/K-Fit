package com.example.k_fit.domain.repositories

import com.example.k_fit.domain.models.CreateNewUser
import com.example.k_fit.data.models.User
import kotlinx.coroutines.flow.Flow

interface IFirebaseRepository {
    fun register(createNewUser: CreateNewUser, password: String): Flow<Result<Unit>>
    suspend fun login(email: String, password: String) : User
}