package com.example.k_fit.domain.di.auth

import com.example.k_fit.data.repositories.FirebaseRepository
import com.example.k_fit.domain.repositories.IFirebaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class registerIFirebaseRepositoryModule {
    @Provides
    @Singleton
    fun bindIFirebaseRepository(firebaseRepository: FirebaseRepository): IFirebaseRepository {
        return firebaseRepository
    }
}