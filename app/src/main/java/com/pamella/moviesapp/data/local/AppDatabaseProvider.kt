package com.pamella.moviesapp.data.local

import android.content.Context
import androidx.room.Room

private const val DATABASE_NAME: String = "FAVORITE_MOVIE_DATABASE"

object AppDatabaseProvider {
    private var appDatabase: AppDatabase? = null

    fun initDatabase(context: Context) {
        appDatabase = Room.databaseBuilder(
            context, AppDatabase::class.java, DATABASE_NAME
        ).build()
    }

    fun getFavoriteDao(): FavoriteMovieDao? {
        appDatabase?.let {
            return it.favoriteDao()
        }
        return null
    }
}