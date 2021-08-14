package com.pamella.moviesapp.classes.usecase

import com.pamella.moviesapp.data.repository.MoviesRepository
import com.pamella.moviesapp.data.repository.MoviesRepositoryImpl

class MovieDetails(private val movieRepository: MoviesRepository = MoviesRepositoryImpl()) {
    fun executeMovie(movieId: Int) = movieRepository.getMovieDetails(movieId)
    fun executeCast(movieId: Int) = movieRepository.getCast(movieId)
    fun executeCertification(movieId: Int) = movieRepository.getCertification(movieId)
}