package com.pamella.moviesapp.data.repository

import com.pamella.moviesapp.data.localsource.database.MovieLocalDataSourceImpl
import com.pamella.moviesapp.data.map.MovieMapper
import com.pamella.moviesapp.data.map.MovieResponseMapper
import com.pamella.moviesapp.classes.model.Movie
import io.reactivex.Single

class FavoriteMoviesRepositoryImpl : FavoriteMoviesRepository {
    private val movieLocalDataSource = MovieLocalDataSourceImpl()
    private val movieMapper = MovieMapper()
    private val movieResponseMapper = MovieResponseMapper()

    override fun addToFavorites(movie: Movie): Single<List<Movie>> {
        val movieMapped = movieResponseMapper.map(movie)
        return movieLocalDataSource
            .addToFavorites(movieMapped)
            .map {
                movieMapper.map(it)
            }
    }

    override fun removeFromFavorites(movie: Movie): Single<List<Movie>> {
        val movieMapped = movieResponseMapper.map(movie)
        return movieLocalDataSource
            .removeFromFavorites(movieMapped)
            .map {
                movieMapper.map(it)
            }
    }

    override fun getFavoriteMovies(): Single<List<Movie>> {
        return movieLocalDataSource
            .getFavoriteMovies()
            .map {
                movieMapper.map(it)
            }
    }
}