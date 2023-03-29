package com.example.k_fit.presentation.features.login

import androidx.lifecycle.viewModelScope
import com.example.k_fit.data.models.User
import com.example.k_fit.domain.usecases.auth.LoginFirebaseUseCase
import com.example.k_fit.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginFirebaseUseCase: LoginFirebaseUseCase,
) : BaseViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    private val _loginResult = MutableStateFlow<User>(User())
    val loginResult: StateFlow<User> = _loginResult
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
                .onStart { _loginResult.value = User() }
                .onEach { result -> _loginResult.value = result }
                .catch { e ->
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