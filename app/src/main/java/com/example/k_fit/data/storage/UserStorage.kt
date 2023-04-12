package com.example.k_fit.data.storage

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.k_fit.data.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "userStorage")

class UserStorage(context: Context) {

    private val dataStore = context.dataStore

    suspend fun saveUser(user: User) {
        dataStore.edit {
            it[EMAIL_KEY] = user.email
            it[FIRST_NAME_KEY] = user.firstName
            it[LAST_NAME_KEY] = user.lastName
            it[NICKNAME_KEY] = user.nickName
            it[GENDER_KEY] = user.gender
        }
    }

    fun getUser() = dataStore.data.map {
        User(
            email = it[EMAIL_KEY] ?: "",
            firstName = it[FIRST_NAME_KEY] ?: "",
            lastName = it[LAST_NAME_KEY] ?: "",
            nickName = it[NICKNAME_KEY] ?: "",
            gender = it[GENDER_KEY] ?: "",
        )
    }

    suspend fun clearUser() {
        dataStore.edit {
            it[EMAIL_KEY] = ""
            it[FIRST_NAME_KEY] = ""
            it[LAST_NAME_KEY] = ""
            it[NICKNAME_KEY] = ""
            it[GENDER_KEY] = ""
        }
        Log.d("ClearUserData", "Data cleared")
    }

    companion object {
        private val EMAIL_KEY = stringPreferencesKey("EMAIL")
        private val FIRST_NAME_KEY = stringPreferencesKey("FIRST_NAME")
        private val LAST_NAME_KEY = stringPreferencesKey("LAST_NAME")
        private val NICKNAME_KEY = stringPreferencesKey("NICKNAME")
        private val GENDER_KEY = stringPreferencesKey("GENDER")
    }
}