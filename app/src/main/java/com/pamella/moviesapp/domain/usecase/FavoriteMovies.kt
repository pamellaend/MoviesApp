package com.pamella.moviesapp.domain.usecase

import com.pamella.moviesapp.data.repository.FavoriteMovies
import com.pamella.moviesapp.domain.model.Movie

class FavoriteMovies(private val favoriteMoviesRepository: FavoriteMovies = FavoriteMovies()) {

    fun getFavoriteMovies() = favoriteMoviesRepository.getFavorite()
    fun addFavoriteMovie(movie: Movie) = favoriteMoviesRepository.addFavorites(movie)
    fun removeFavoriteMovie(movie: Movie) = favoriteMoviesRepository.removeFavorites(movie)
}