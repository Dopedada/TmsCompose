package com.example.tmscompose

import android.app.Application
import android.content.Context

class TmsApp : Application() {

    companion object {
        @JvmStatic
        lateinit var appContext: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

    }
}