package com.example.k_fit.domain.usecases.auth

import com.example.k_fit.domain.models.CreateNewUser
import com.example.k_fit.domain.repositories.IFirebaseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class RegisterFirebaseUseCase @Inject constructor(private val repository: IFirebaseRepository) {
    open operator fun invoke(createNewUser: CreateNewUser, password: String) =
        repository.register(createNewUser, password)
}