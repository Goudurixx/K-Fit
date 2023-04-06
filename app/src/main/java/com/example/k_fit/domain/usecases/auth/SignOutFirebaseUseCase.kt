package com.example.k_fit.domain.usecases.auth

import com.example.k_fit.domain.repositories.IFirebaseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class SignOutFirebaseUseCase @Inject constructor(private val repository: IFirebaseRepository) {
    open operator fun invoke() =
        repository.signOut()
}