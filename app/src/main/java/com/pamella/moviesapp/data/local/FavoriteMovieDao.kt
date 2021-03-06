package com.pamella.moviesapp.data.local

import androidx.room.*

@Dao
interface FavoriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg moviesData: MovieData)

    @Query("SELECT * FROM movieData")
    fun getAll(): List<MovieData>

    @Delete
    fun delete(movieData: MovieData)
}