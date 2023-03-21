package com.example.k_fit.data.datasources

import com.example.k_fit.data.models.NewUser
import com.example.k_fit.data.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class FirebaseDataSource @Inject constructor() {
    private val auth: FirebaseAuth = Firebase.auth
    private val db = Firebase.firestore

    fun register(newUser: NewUser, password: String) {
        auth.createUserWithEmailAndPassword(newUser.email, password)
            .addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    db.collection("users").document(auth.currentUser!!.uid).set(newUser)
                        .addOnCompleteListener {
                            println("User successfully create")
                        }
                } else {
                    println("Register Failed" + result.exception)
                }
            }
    }

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { result ->
            if (result.isSuccessful) {
                val userInfo = auth.currentUser
                val docRef = db.collection("users").document(userInfo!!.uid)
                docRef.get().addOnSuccessListener { documentSnapshot ->
                    val currentUser = documentSnapshot.toObject(User::class.java)
                }
            } else {
                println("Login failed because " + result.exception)
            }
        }
    }
}