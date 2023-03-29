package com.example.k_fit.data.repositories

import com.example.k_fit.data.datasources.FirebaseDataSource
import com.example.k_fit.data.mappers.toService
import com.example.k_fit.domain.models.CreateNewUser
import com.example.k_fit.domain.repositories.IFirebaseRepository
import javax.inject.Inject
import javax.inject.Singleton
import com.example.k_fit.data.models.User
import kotlinx.coroutines.flow.Flow

@Singleton
class FirebaseRepository @Inject constructor(private val firebaseDataSource: FirebaseDataSource) :
    IFirebaseRepository {

    override fun register(createNewUser: CreateNewUser, password: String): Flow<Result<Unit>> {
        return firebaseDataSource.register(createNewUser.toService(), password)
    }

    override suspend fun login(email: String, password: String): User {
        return firebaseDataSource.login(email, password)
    }
}