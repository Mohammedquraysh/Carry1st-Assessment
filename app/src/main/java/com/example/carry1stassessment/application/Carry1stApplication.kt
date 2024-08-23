package com.example.carry1stassessment.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Carry1stApplication : Application() {

    companion object {
        @get:Synchronized
        lateinit var application: Carry1stApplication
            private set
    }
}