package com.pamella.moviesapp.activitys.app

import android.app.Application
import com.pamella.moviesapp.data.localsource.database.AppDatabaseProvider

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabaseProvider.initDatabase(applicationContext)
    }
}