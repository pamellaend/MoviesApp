package com.pamella.moviesapp.domain.usecase

import com.pamella.moviesapp.data.repository.MoviesRepository

class Genres(private val repository: MoviesRepository = MoviesRepository()) {
    fun executeGenres() = repository.getAllGenres()
}