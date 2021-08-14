package com.pamella.moviesapp.data.map

import com.pamella.moviesapp.data.model.movies.MovieDetailResponse
import com.pamella.moviesapp.classes.model.MovieDetail

class MovieDetailMapper {
    fun map(movieResponse: MovieDetailResponse): MovieDetail {
        val movieDetailed = MovieDetail(
            backdropPath = movieResponse.backdropPath,
            genres = movieResponse.genres,
            id = movieResponse.id,
            overview = movieResponse.overview,
            releaseDate = movieResponse.releaseDate,
            runtime = movieResponse.runtime,
            voteAverage = movieResponse.voteAverage,
            title = movieResponse.title,
            isFavorite = movieResponse.isFavorite
        )
        return movieDetailed
    }
}