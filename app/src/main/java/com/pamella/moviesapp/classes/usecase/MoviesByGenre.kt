package com.pamella.moviesapp.classes.usecase

import com.pamella.moviesapp.data.repository.MoviesRepository
import com.pamella.moviesapp.data.repository.MoviesRepositoryImpl

class MoviesByGenre(private val repository: MoviesRepository = MoviesRepositoryImpl()) {
    fun executeMoviesByGenre(genresId: String) = repository.getMoviesByGenre(genresId)
}