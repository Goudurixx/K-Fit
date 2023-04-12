package com.example.k_fit.presentation.features.userProfile

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.k_fit.data.storage.UserStorage
import com.example.k_fit.domain.usecases.auth.SignOutFirebaseUseCase
import com.example.k_fit.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val signOutFirebaseUseCase: SignOutFirebaseUseCase,
) : BaseViewModel() {

    fun signOut() {
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            Log.e("Error: ", "SignOut request failed")
        }) {
            signOutFirebaseUseCase()
        }
    }
}