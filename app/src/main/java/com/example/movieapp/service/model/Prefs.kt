package com.example.movieapp.service.model

import android.content.Context

const val SHARED_NAME = "MyDB"
const val SHARED_USER_NAME = "username"
const val SHARED_PASSWORD = "password"
const val SHARED_IS_LOGGED = "is_logged"

class Prefs(context: Context) {
    private val storage = context.getSharedPreferences(SHARED_NAME, 0)

    init {
        this.saveUserName("sharon")
        this.savePassword("1234")
    }

    fun saveUserName(name: String) {
        storage.edit().putString(SHARED_USER_NAME, name).apply()
    }

    fun savePassword(password: String) {
        storage.edit().putString(SHARED_PASSWORD, password).apply()
    }

    fun saveIsLogged(value: Boolean) {
        storage.edit().putBoolean(SHARED_IS_LOGGED, value).apply()
    }

    fun getUserName(): String {
        return storage.getString(SHARED_USER_NAME, "").toString()
    }

    fun getIsLogged(): Boolean {
        return storage.getBoolean(SHARED_IS_LOGGED, false)
    }

    fun isLoggedIn(): Boolean {
        return getIsLogged()
    }
    
    fun wipe() {
        storage.edit().clear().apply()
    }
}
