package com.example.k_fit.data.datasources

import android.util.Log
import com.example.k_fit.data.models.NewUser
import com.example.k_fit.data.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirebaseDataSource @Inject constructor() {
    private val auth: FirebaseAuth = Firebase.auth
    private val db = Firebase.firestore

    fun register(newUser: NewUser, password: String): Flow<Result<Unit>> = flow {
        try {
            val result = auth.createUserWithEmailAndPassword(newUser.email, password).await()
            val uid = result.user?.uid ?: throw IllegalStateException("User ID is null")
            if (uid.isNotEmpty())
                db.collection("users").document(auth.currentUser!!.uid).set(newUser)
                    .addOnCompleteListener {
                        println("User successfully create")
                    }
            emit(Result.success(Unit))
        } catch (e: Exception) {
            Log.e("Firebase register error: ", e.toString())
            emit(Result.failure(e))
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
                Log.e("Login Failed ", result.exception.toString())
                continuation.resume(currentUser)
            }
        }
    }
}