package com.example.k_fit.data.datasources

import android.util.Log
import com.example.k_fit.data.models.NewUser
import com.example.k_fit.data.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

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

    suspend fun login(email: String, password: String): User = suspendCoroutine { continuation ->
        var currentUser: User = User()

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { result ->
            if (result.isSuccessful) {
                val userInfo = auth.currentUser
                val docRef = db.collection("users").document(userInfo!!.uid)
                docRef.get().addOnSuccessListener { documentSnapshot ->
                    currentUser = documentSnapshot.toObject(User::class.java)!!
                    continuation.resume(currentUser)
                }
            } else {
                Log.d("Login Failed ", result.exception.toString())
                continuation.resume(currentUser)
            }
        }
    }
}