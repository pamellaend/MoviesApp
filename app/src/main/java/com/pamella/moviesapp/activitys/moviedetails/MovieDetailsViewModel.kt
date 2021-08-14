package com.pamella.moviesapp.activitys.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pamella.moviesapp.classes.model.Cast
import com.pamella.moviesapp.classes.model.Certification
import com.pamella.moviesapp.classes.model.Movie
import com.pamella.moviesapp.classes.model.MovieDetail
import com.pamella.moviesapp.classes.usecase.MovieDetails
import com.pamella.moviesapp.activitys.model.ViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsViewModel : ViewModel() {

    private val getMovieDetailsUseCase = MovieDetails()
    private val _movieLiveData = MutableLiveData<MovieDetail>()
    val movieLiveData: LiveData<MovieDetail> = _movieLiveData
    private val _castLiveData = MutableLiveData<List<Cast>>()
    val castLiveData: LiveData<List<Cast>> = _castLiveData
    private val _certificationLiveData = MutableLiveData<List<Certification>>()
    val certificationLiveData: LiveData<List<Certification>> = _certificationLiveData
    private val _favoriteMoviesLiveData = MutableLiveData<List<Movie>>(mutableListOf())
    val favoriteMoviesLiveData: LiveData<List<Movie>> = _favoriteMoviesLiveData
    private val _viewStateLiveData = MutableLiveData<ViewState>()
    val viewStateLiveData: LiveData<ViewState> = _viewStateLiveData
    private val disposable = CompositeDisposable()

    fun getMovieDetails(movieId: Int) {
        getMovieDetailsUseCase.executeMovie(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    _movieLiveData.value = result
                },
                {
                    _viewStateLiveData.value = ViewState.GeneralError
                }
            ).handleDisposable()
    }


    fun getCertification(movieId: Int) {
        getMovieDetailsUseCase.executeCertification(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    _certificationLiveData.value = result
                },
                {
                    _viewStateLiveData.value = ViewState.GeneralError
                }
            ).handleDisposable()
    }

    fun getCast(movieId: Int) {
        getMovieDetailsUseCase.executeCast(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    _castLiveData.value = result
                },
                {
                    _viewStateLiveData.value = ViewState.GeneralError
                }
            ).handleDisposable()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    private fun Disposable.handleDisposable(): Disposable = apply { disposable.add(this) }
}