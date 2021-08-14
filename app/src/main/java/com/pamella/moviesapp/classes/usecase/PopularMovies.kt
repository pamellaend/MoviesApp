package com.pamella.moviesapp.classes.usecase

import com.pamella.moviesapp.data.repository.MoviesRepository
import com.pamella.moviesapp.data.repository.MoviesRepositoryImpl

class PopularMovies(private val moviesRepository: MoviesRepository = MoviesRepositoryImpl()) {
    fun execute() = moviesRepository.getPopularMovies()
}