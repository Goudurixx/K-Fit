package com.example.k_fit.presentation.features.register

import com.example.k_fit.presentation.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : BaseViewModel() {
    private val auth: FirebaseAuth = Firebase.auth

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _passwordConfirmation = MutableStateFlow("")
    val passwordConfirmation: StateFlow<String> = _passwordConfirmation

    private val _isPasswordDifferent = MutableStateFlow(false)
    val isPasswordDifferent: StateFlow<Boolean> = _isPasswordDifferent
    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }
    fun updatePasswordConfirmation(confirmPassword: String) {
        _passwordConfirmation.value = confirmPassword
    }

    // Register user
    fun registerUser(home: () -> Unit) {
        val email: String = _email.value
        val password: String =
            _password.value
        val passwordConfirm: String = _passwordConfirmation.value

        if(password == passwordConfirm){
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        home()
                    }
                }
        }else{
            _isPasswordDifferent.value = true
        }


    }
}