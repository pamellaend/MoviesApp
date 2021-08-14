package com.pamella.moviesapp.data.localsource.database

import androidx.room.*
import com.pamella.moviesapp.data.localsource.database.MovieData

@Dao
interface FavoriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg moviesData: MovieData)

    @Query("SELECT * FROM movieData")
    fun getAll(): List<MovieData>

    @Delete
    fun delete(movieData: MovieData)
}