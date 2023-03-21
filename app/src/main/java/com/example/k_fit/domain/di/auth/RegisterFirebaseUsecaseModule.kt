package com.example.k_fit.domain.di.auth

import com.example.k_fit.domain.repositories.IFirebaseRepository
import com.example.k_fit.domain.usecases.auth.RegisterFirebaseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RegisterFirebaseUsecaseModule {
    @Provides
    fun provideRegisterFirebaseUseCase(iFirebaseRepository: IFirebaseRepository): RegisterFirebaseUseCase {
        return RegisterFirebaseUseCase(iFirebaseRepository)
    }
}