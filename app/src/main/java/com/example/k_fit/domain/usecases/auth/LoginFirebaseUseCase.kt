package com.example.k_fit.domain.usecases.auth

import com.example.k_fit.domain.repositories.IFirebaseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class LoginFirebaseUseCase @Inject constructor(private val repository: IFirebaseRepository) {
    open suspend operator fun invoke(email: String, password: String) =
        repository.login(email, password)
}