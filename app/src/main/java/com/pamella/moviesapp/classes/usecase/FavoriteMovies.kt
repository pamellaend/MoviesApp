package com.pamella.moviesapp.classes.usecase

import com.pamella.moviesapp.data.repository.FavoriteMoviesRepository
import com.pamella.moviesapp.data.repository.FavoriteMoviesRepositoryImpl
import com.pamella.moviesapp.classes.model.Movie

class FavoriteMovies(private val favoriteMoviesRepository: FavoriteMoviesRepository = FavoriteMoviesRepositoryImpl()) {

    fun getFavoriteMovies() = favoriteMoviesRepository.getFavoriteMovies()
    fun addFavoriteMovie(movie: Movie) = favoriteMoviesRepository.addToFavorites(movie)
    fun removeFavoriteMovie(movie: Movie) = favoriteMoviesRepository.removeFromFavorites(movie)
}