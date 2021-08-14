package com.pamella.moviesapp.data.repository

import com.pamella.moviesapp.classes.model.Movie
import io.reactivex.Single

interface FavoriteMoviesRepository {
    fun addToFavorites(movie: Movie): Single<List<Movie>>
    fun removeFromFavorites(movie: Movie): Single<List<Movie>>
    fun getFavoriteMovies(): Single<List<Movie>>
}