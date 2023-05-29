package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movieapp.view.ui.LoginApplication.Companion.prefs

class LogoutViewModel: ViewModel() {

    fun getUsername(): String {
        return prefs.getUserName()
    }

    fun clearSession() {
        prefs.wipe()
    }
}
