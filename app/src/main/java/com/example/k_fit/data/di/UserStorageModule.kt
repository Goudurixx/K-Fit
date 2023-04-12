package com.example.k_fit.data.di

import com.example.k_fit.data.storage.UserStorage
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UserStorageModule {
    @Provides
    fun provideUserStorage(@ApplicationContext appContext: Context): UserStorage {
        return UserStorage(appContext)
    }
}