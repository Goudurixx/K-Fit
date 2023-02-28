package com.example.k_fit.presentation.features.register

import com.example.k_fit.presentation.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : BaseViewModel() {
    private val auth: FirebaseAuth = Firebase.auth

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState

    fun updateScreenStep(step: Float) {
        _registerState.update { currentState ->
            currentState.copy(screenStep = step)
        }
    }
    fun updateEmail(newEmail: String) {
        _registerState.update { currentState ->
            currentState.copy(email = newEmail)
        }
    }

    fun updatePassword(newPassword: String) {
        _registerState.update { currentState ->
            currentState.copy(password = newPassword)
        }
    }

    fun updatePasswordConfirmation(confirmPassword: String) {
        _registerState.update { currentState ->
            currentState.copy(passwordConfirm = confirmPassword)
        }
    }

    private fun updateIsEmailValid(email: String?): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$"
        pattern = Pattern.compile(EMAIL_PATTERN)
        matcher = pattern.matcher(email)
        _registerState.update { currentState ->
            currentState.copy(isEmailValid = matcher.matches())
        }
        return _registerState.value.isEmailValid
    }

    private fun updateIsPasswordValid(password: String?) {
        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=-])(?=\\S+$).{6,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(password)
        _registerState.update { currentState ->
            currentState.copy(isPasswordValid = matcher.matches())
        }
    }

    private fun updateIsPasswordDifferent(password: String, passwordConfirm: String) {
        _registerState.update { currentState ->
            currentState.copy(isPasswordDifferent = (password != passwordConfirm))
        }
    }

    fun updateNickName(nickName: String) {
        _registerState.update { currentState ->
            currentState.copy(nickName = nickName)
        }
    }

    fun updateFirstName(firstName: String) {
        _registerState.update { currentState ->
            currentState.copy(firstName = firstName)
        }
    }

    fun updateLastName(lastName: String) {
        _registerState.update { currentState ->
            currentState.copy(lastName = lastName)
        }
    }

    fun updateBirthdate(birthDate: Date) {
        _registerState.update { currentState ->
            currentState.copy(birthDate = birthDate)
        }
    }

    private fun isFormValid(): Boolean {
        val email: String = _registerState.value.email
        val password: String = _registerState.value.password
        val passwordConfirm: String = _registerState.value.passwordConfirm

        updateIsEmailValid(email)
        updateIsPasswordValid(password)
        updateIsPasswordDifferent(password, passwordConfirm)

        return _registerState.value.isEmailValid && _registerState.value.isPasswordValid && !_registerState.value.isPasswordDifferent
    }

    // Register user
    fun registerUser(home: () -> Unit) {
        if (isFormValid()) {
            auth.createUserWithEmailAndPassword(
                _registerState.value.email,
                _registerState.value.password
            )
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        home()
                    }
                }
        }
    }
}