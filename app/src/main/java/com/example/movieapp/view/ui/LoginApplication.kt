package com.example.movieapp.view.ui

import android.app.Application
import com.example.movieapp.model.Prefs

class LoginApplication: Application() {
    companion object{
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }
}