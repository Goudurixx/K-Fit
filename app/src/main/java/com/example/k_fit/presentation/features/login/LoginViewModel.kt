package com.example.k_fit.presentation.features.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.k_fit.domain.usecases.auth.LoginFirebaseUseCase
import com.example.k_fit.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginFirebaseUseCase: LoginFirebaseUseCase,
) : BaseViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    fun updateEmail(newEmail: String) {
        _loginState.update { currentState ->
            currentState.copy(email = newEmail)
        }
    }

    fun updatePassword(password: String) {
        _loginState.update { currentState ->
            currentState.copy(password = password)
        }
    }

    fun login(redirection: () -> Unit) {
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            println("Error to login request")
        }) {
            loginFirebaseUseCase(_loginState.value.email, _loginState.value.password)
                .catch { e ->
                    Log.e("Firebase Login Error: ", e.message.toString())
                    _loginState.update { currentState ->
                        currentState.copy(errorMessage = true)
                    }
                }
                .collect {
                    redirection()
                }
        }
    }
}