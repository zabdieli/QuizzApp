package com.esiea.quizzapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class QuizzApplication : Application() {
    override fun onCreate(){
        super.onCreate()
    }
}
