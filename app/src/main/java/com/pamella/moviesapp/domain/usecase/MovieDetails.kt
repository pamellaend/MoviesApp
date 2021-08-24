package com.pamella.moviesapp.domain.usecase

import com.pamella.moviesapp.data.repository.MoviesRepository

class MovieDetails(private val movieRepository: MoviesRepository = MoviesRepository()) {
    fun executeMovie(movieId: Int) = movieRepository.getMovieDetails(movieId)
    fun executeCast(movieId: Int) = movieRepository.getCast(movieId)
    fun executeCertification(movieId: Int) = movieRepository.getCertification(movieId)
}