package com.pamella.moviesapp.activitys

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pamella.moviesapp.classes.model.Genre
import com.pamella.moviesapp.classes.model.Movie
import com.pamella.moviesapp.classes.usecase.*
import com.pamella.moviesapp.activitys.model.ViewState
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

    private val _moviesLiveData = MutableLiveData<List<Movie>>(mutableListOf())
    val movieListLiveData: LiveData<List<Movie>> = _moviesLiveData
    private val _genresLiveData = MutableLiveData<List<Genre>>()
    val genreListLiveData: LiveData<List<Genre>> = _genresLiveData
    private val _favoriteMoviesLiveData = MutableLiveData<List<Movie>>(mutableListOf())
    val favoriteMoviesLiveData: LiveData<List<Movie>> = _favoriteMoviesLiveData
    private val _searchResultsLiveData = MutableLiveData<List<Movie>>(mutableListOf())
    val searchResultsLiveData: LiveData<List<Movie>> = _searchResultsLiveData
    private val _viewStateLiveData = MutableLiveData<ViewState>()
    val viewStateLiveData: LiveData<ViewState> = _viewStateLiveData
    private val disposable = CompositeDisposable()

    fun getPopularMovies() {
        getPopularMoviesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    _moviesLiveData.value = result
                },
                {
                    Log.e("Error Req", "error: " + it.cause)
                    _viewStateLiveData.value = ViewState.GeneralError
                }
            ).handleDisposable()
    }


    fun getGenres() {
        getGenresUseCase.executeGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    _genresLiveData.value = result
                },
                {
                    Log.e("ErrorReq", "error: " + it.cause)
                    _viewStateLiveData.value = ViewState.GeneralError
                }
            ).handleDisposable()
    }

    fun getMoviesByGenre(genresId: List<Int>) {
        getMoviesByGenreUseCase.executeMoviesByGenre(genresId.joinToString(","))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    _moviesLiveData.value = result
                },
                {
                    Log.e("Error Req", "error: " + it.cause)
                    _viewStateLiveData.value = ViewState.GeneralError
                }
            ).handleDisposable()
    }

    fun addToFavorites(movie: Movie) {
        favoriteMoviesUseCase.addFavoriteMovie(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _favoriteMoviesLiveData.value = it
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
                    _favoriteMoviesLiveData.value = it
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
                    _searchResultsLiveData.value = it
                    if (it.isEmpty()) {
                        _viewStateLiveData.value = ViewState.MovieNotFound
                    }
                },
                {
                    Log.e("Search Error ", "Mensagem do erro: " + it.message)
                    _viewStateLiveData.value = ViewState.GeneralError
                }
            ).handleDisposable()
    }

    fun removeFromFavorites(movieToRemove: Movie) {
        favoriteMoviesUseCase.removeFavoriteMovie(movieToRemove)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _favoriteMoviesLiveData.value = it
                    val result = _moviesLiveData.value?.find { movie ->
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

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    private fun Disposable.handleDisposable(): Disposable = apply { disposable.add(this) }

}