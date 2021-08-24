package com.pamella.moviesapp.domain.usecase

import com.pamella.moviesapp.data.repository.MoviesRepository

class PopularMovies(private val moviesRepository: MoviesRepository = MoviesRepository()) {
    fun execute() = moviesRepository.getPopMovies()
}