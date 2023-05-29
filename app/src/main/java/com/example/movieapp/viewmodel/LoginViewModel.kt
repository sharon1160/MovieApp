package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movieapp.view.ui.LoginApplication.Companion.prefs

class LoginViewModel: ViewModel() {

    fun validEntries(username: String, password: String): Boolean {
        return username == "sharon" && password == "1234"
    }

    fun saveSession(username: String, password: String) {
        prefs.saveUserName(username)
        prefs.savePassword(password)
        prefs.saveIsLogged(true)
    }
}
