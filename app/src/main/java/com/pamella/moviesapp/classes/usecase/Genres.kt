package com.pamella.moviesapp.classes.usecase

import com.pamella.moviesapp.data.repository.MoviesRepository
import com.pamella.moviesapp.data.repository.MoviesRepositoryImpl

class Genres(private val repository: MoviesRepository = MoviesRepositoryImpl()) {
    fun executeGenres() = repository.getAllGenres()
}