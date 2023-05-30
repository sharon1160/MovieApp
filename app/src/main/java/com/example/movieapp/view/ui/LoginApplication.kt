package com.example.movieapp.view.ui

import android.app.Application
import com.example.movieapp.service.model.Prefs

class LoginApplication: Application() {
    companion object{
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }
}