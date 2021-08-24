package com.pamella.moviesapp.domain.usecase

import com.pamella.moviesapp.data.repository.MoviesRepository

class MoviesByGenre(private val repository: MoviesRepository = MoviesRepository()) {
    fun executeMoviesByGenre(genresId: String) = repository.getMoviesByGenre(genresId)
}