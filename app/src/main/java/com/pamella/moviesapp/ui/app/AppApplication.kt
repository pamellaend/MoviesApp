package com.pamella.moviesapp.ui.app

import android.app.Application
import com.pamella.moviesapp.data.local.AppDatabaseProvider

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabaseProvider.initDatabase(applicationContext)
    }
}