package com.pamella.moviesapp.data.map

import com.pamella.moviesapp.data.model.movies.MovieResponse
import com.pamella.moviesapp.domain.model.Movie

class MovieMapper {
    fun map(movieResponseList: List<MovieResponse>): List<Movie> {
        val movies = mutableListOf<Movie>()
        movieResponseList.forEach {
            val movie = Movie(
                imgHome = it.imgHome,
                id = it.id,
                title = it.title,
                rating = it.rating,
                genreIds = it.genreIds,
                isFavorite = it.isFavorite
            )
            movies.add(movie)
        }
        return movies
    }
}