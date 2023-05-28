package com.example.movieapp.model

import android.content.Context

const val SHARED_NAME = "MyDB"
const val SHARED_USER_NAME = "username"
const val SHARED_PASSWORD = "password"

class Prefs(context: Context) {
    private val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveUserName(name: String) {
        storage.edit().putString(SHARED_USER_NAME, name).apply()
    }

    fun savePassword(password: String) {
        storage.edit().putString(SHARED_PASSWORD, password).apply()
    }

    fun getUserName(): String {
        return storage.getString(SHARED_USER_NAME, "").toString()
    }

    fun getPassword(): String {
        return storage.getString(SHARED_PASSWORD, "").toString()
    }

    fun isLoggedIn(): Boolean {
        return getUserName().isNotEmpty() && getPassword().isNotEmpty()
    }
}