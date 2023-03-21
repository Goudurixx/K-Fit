package com.example.k_fit.data.repositories

import com.example.k_fit.data.datasources.FirebaseDataSource
import com.example.k_fit.data.mappers.toService
import com.example.k_fit.domain.models.CreateNewUser
import com.example.k_fit.domain.repositories.IFirebaseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRepository @Inject constructor(private val firebaseDataSource: FirebaseDataSource) :
    IFirebaseRepository {

    override fun register(createNewUser: CreateNewUser, password: String) {
        return firebaseDataSource.register(createNewUser.toService(), password)
    }

    override fun login(email: String, password: String) {
        return firebaseDataSource.login(email, password)
    }
}