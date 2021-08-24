package com.pamella.moviesapp.data.repository

import com.pamella.moviesapp.data.local.MovieLocalDataSourceImpl
import com.pamella.moviesapp.data.map.MovieMapper
import com.pamella.moviesapp.data.map.MovieResponseMapper
import com.pamella.moviesapp.domain.model.Movie
import io.reactivex.Single

class FavoriteMovies {
    private val movieLocalDataSource = MovieLocalDataSourceImpl()
    private val movieMapper = MovieMapper()
    private val movieResponseMapper = MovieResponseMapper()


    fun removeFavorites(movie: Movie): Single<List<Movie>> {
        val movieMapped = movieResponseMapper.map(movie)
        return movieLocalDataSource
            .removeFromFavorites(movieMapped)
            .map {
                movieMapper.map(it)
            }
    }


    fun getFavorite(): Single<List<Movie>> {
        return movieLocalDataSource
            .getFavoriteMovies()
            .map {
                movieMapper.map(it)
            }
    }

    fun addFavorites(movie: Movie): Single<List<Movie>> {
        val movieMapped = movieResponseMapper.map(movie)
        return movieLocalDataSource
            .addToFavorites(movieMapped)
            .map {
                movieMapper.map(it)
            }
    }


}