package com.pamella.moviesapp.ui.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pamella.moviesapp.data.local.MovieLocalDataSourceImpl
import com.pamella.moviesapp.domain.model.Genre
import com.pamella.moviesapp.domain.model.Movie
import com.pamella.moviesapp.domain.usecase.*
import com.pamella.moviesapp.ui.model.ViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MoviesViewModel : ViewModel() {

    private val getPopularMoviesUseCase = PopularMovies()
    private val getGenresUseCase = Genres()
    private val getMoviesByGenreUseCase = MoviesByGenre()
    private val favoriteMoviesUseCase = FavoriteMovies()
    private val searchForMoviesUseCase = SearchForMovie()

    val moviesLiveData = MutableLiveData<List<Movie>>(mutableListOf())
    val genresLiveData = MutableLiveData<List<Genre>>()
    val favoriteMoviesLiveData = MutableLiveData<List<Movie>>(mutableListOf())
    val searchResultsLiveData = MutableLiveData<List<Movie>>(mutableListOf())
    val viewStateLiveData = MutableLiveData<ViewState>()
    private val disposable = CompositeDisposable()

    fun getPopularMovies() {
        getPopularMoviesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    moviesLiveData.value = checkFavorite(result)
                },
                {
                    Log.e("Error Req", "error: " + it.cause)
                    viewStateLiveData.value = ViewState.GeneralError
                }
            ).handleDisposable()
    }


    fun getGenres() {
        getGenresUseCase.executeGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    genresLiveData.value = result
                },
                {
                    Log.e("ErrorReq", "error: " + it.cause)
                    viewStateLiveData.value = ViewState.GeneralError
                }
            ).handleDisposable()
    }

    fun getMoviesByGenre(genresId: List<Int>) {
        getMoviesByGenreUseCase.executeMoviesByGenre(genresId.joinToString(","))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    moviesLiveData.value = checkFavorite(result)
                },
                {
                    Log.e("Error Req", "error: " + it.cause)
                    viewStateLiveData.value = ViewState.GeneralError
                }
            ).handleDisposable()
    }

    fun addToFavorites(movie: Movie) {
        favoriteMoviesUseCase.addFavoriteMovie(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    favoriteMoviesLiveData.value = it
                },
                {
                    print(it.message)
                }
            ).handleDisposable()
    }

    fun getFavoriteMovies() {
        favoriteMoviesUseCase.getFavoriteMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    favoriteMoviesLiveData.value = it
                },
                {
                    print(it.message)
                }
            ).handleDisposable()
    }


    fun searchForMovie(movieSearched: Uri) {
        searchForMoviesUseCase.executeSearch(movieSearched)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    searchResultsLiveData.value = checkFavorite(it)
                    if (it.isEmpty()) {
                        viewStateLiveData.value = ViewState.MovieNotFound
                    }
                },
                {
                    Log.e("Search Error ", "Mensagem do erro: " + it.message)
                    viewStateLiveData.value = ViewState.GeneralError
                }
            ).handleDisposable()
    }

    fun removeFromFavorites(movieToRemove: Movie) {
        favoriteMoviesUseCase.removeFavoriteMovie(movieToRemove)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    favoriteMoviesLiveData.value = checkFavorite(it)
                    val result = moviesLiveData.value?.find { movie ->
                        movie.id == movieToRemove.id
                    }
                    result?.let { movie ->
                        movie.isFavorite = false
                    }
                },
                {
                    print(it.message)
                }
            ).handleDisposable()
    }

    fun checkFavorite(movies:List<Movie>) : List<Movie>{
        val favoriteList = MovieLocalDataSourceImpl().dao.toString()
        return movies.map { movie->
            val title = movie.title
            title?.let{
            movie.isFavorite = favoriteList.contains(title,ignoreCase = true)}
            movie
        }
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    private fun Disposable.handleDisposable(): Disposable = apply { disposable.add(this) }

}