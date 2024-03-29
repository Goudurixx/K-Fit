package com.example.k_fit.data.datasources

import android.util.Log
import com.example.k_fit.data.models.NewUser
import com.example.k_fit.data.models.User
import com.example.k_fit.data.storage.UserStorage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseDataSource @Inject constructor(private val userStorage: UserStorage) {
    private val auth: FirebaseAuth = Firebase.auth
    private val db = Firebase.firestore
    fun register(newUser: NewUser, password: String): Flow<Result<Unit>> = flow {
        try {
            val result = auth.createUserWithEmailAndPassword(newUser.email, password).await()
            val uid = result.user?.uid ?: throw IllegalStateException("User ID is null")
            if (uid.isNotEmpty())
                db.collection("users").document(uid).set(newUser)
                    .addOnCompleteListener {
                        println("User successfully create")
                    }
            emit(Result.success(Unit))
        } catch (e: Exception) {
            Log.e("Firebase register error: ", e.toString())
            emit(Result.failure(e))
        }
    }

    fun login(email: String, password: String): Flow<User> = flow {
        var currentUser: User = User()
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            val userInfo = auth.currentUser
            val docRef = db.collection("users").document(userInfo!!.uid)
            suspendCancellableCoroutine<User> { continuation ->
                docRef.get().addOnSuccessListener { documentSnapshot ->
                    currentUser = documentSnapshot.toObject(User::class.java)!!
                    continuation.resume(currentUser, {})
                }.addOnFailureListener { exception ->
                    continuation.cancel(exception)
                }
            }
            emit(currentUser)
        } catch (e: Exception) {
            Log.e("Firebase login error: ", e.toString())
            throw e
        }
    }.onEach { user ->
        if (user.firstName.isNotBlank())
            userStorage.saveUser(user)
    }.flowOn(Dispatchers.IO)

    suspend fun signOut() {
        try {
            auth.signOut()
            userStorage.clearUser()
        } catch (e: Exception) {
            Log.e("Firebase disconnect error: ", e.toString())
            throw e
        }
    }
}