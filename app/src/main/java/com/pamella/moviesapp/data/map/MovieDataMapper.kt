package com.pamella.moviesapp.data.map

import com.pamella.moviesapp.data.localsource.database.MovieData
import com.pamella.moviesapp.data.model.movies.MovieResponse

class MovieDataMapper {
    fun map(movie: MovieResponse): MovieData {
        return MovieData(
            imgHome = movie.imgHome,
            id = movie.id,
            title = movie.title,
            rating = movie.rating,
            genreIds = movie.genreIds.joinToString(),
            isFavorite = movie.isFavorite
        )
    }
}