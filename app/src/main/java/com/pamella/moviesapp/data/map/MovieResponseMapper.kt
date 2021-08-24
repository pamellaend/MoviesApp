package com.pamella.moviesapp.data.map

import com.pamella.moviesapp.data.local.MovieData
import com.pamella.moviesapp.data.model.movies.MovieResponse
import com.pamella.moviesapp.domain.model.Movie

class MovieResponseMapper {
    fun map(movie: Movie): MovieResponse {
        return MovieResponse(
            imgHome = movie.imgHome,
            id = movie.id,
            title = movie.title,
            rating = movie.rating,
            genreIds = movie.genreIds,
            isFavorite = movie.isFavorite
        )
    }

    fun map(movie: MovieData): MovieResponse {
        return MovieResponse(
            imgHome = movie.imgInitial,
            id = movie.id,
            title = movie.title,
            rating = movie.rating,
            genreIds = movie.genreIds.asIntList(),
            isFavorite = movie.isFavorite
        )
    }

    private fun String.asIntList(): List<Int> {
        return this.split(",").map { it.trim().toInt() }
    }
}