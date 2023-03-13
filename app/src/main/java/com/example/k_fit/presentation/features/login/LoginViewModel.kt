package com.example.k_fit.presentation.features.login

import com.example.k_fit.presentation.base.BaseViewModel
import com.example.k_fit.presentation.common.Gender
import com.example.k_fit.presentation.features.register.RegisterProfileState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val db = Firebase.firestore

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

    fun login() {
        auth.signInWithEmailAndPassword(loginState.value.email, loginState.value.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    println("UID " + user!!.uid)
                    val docRef = db.collection("user").document(user!!.uid)
                    println("DocRef" + docRef)
                    docRef.get().addOnSuccessListener { documentSnapshot ->
                        val currentUser = documentSnapshot.toObject(User::class.java)
                        println("Current user " + currentUser!!.firstName)
                    }
                } else {
                    println("Login failed")
                }
            }
    }
}

class User {
    var email: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var nickName: String = ""
    var gender: String = ""

    constructor()
    constructor(
        email: String,
        firstName: String,
        lastName: String,
        nickName: String,
        gender: String
        ){
        this.email = email
        this.firstName = firstName
        this.lastName = lastName
        this.nickName = nickName
        this.gender = gender
    }
}
