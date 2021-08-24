package com.pamella.moviesapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pamella.moviesapp.domain.model.Cast
import com.pamella.moviesapp.domain.model.ParentalGuidance
import com.pamella.moviesapp.domain.model.MovieDetail
import com.pamella.moviesapp.domain.usecase.MovieDetails
import com.pamella.moviesapp.ui.model.ViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsViewModel : ViewModel() {

    private val getMovieDetailsUseCase = MovieDetails()
    val movieLiveData = MutableLiveData<MovieDetail>()
    val castLiveData = MutableLiveData<List<Cast>>()
    val certificationLiveData = MutableLiveData<List<ParentalGuidance>>()
    val viewStateLiveData = MutableLiveData<ViewState>()
    private val disposable = CompositeDisposable()

    fun getMovieDetails(movieId: Int) {
        getMovieDetailsUseCase.executeMovie(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    movieLiveData.value = result
                },
                {
                    viewStateLiveData.value = ViewState.GeneralError
                }
            ).handleDisposable()
    }


    fun getCertification(movieId: Int) {
        getMovieDetailsUseCase.executeCertification(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    certificationLiveData.value = result
                },
                {
                    viewStateLiveData.value = ViewState.GeneralError
                }
            ).handleDisposable()
    }

    fun getCast(movieId: Int) {
        getMovieDetailsUseCase.executeCast(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    castLiveData.value = result
                },
                {
                    viewStateLiveData.value = ViewState.GeneralError
                }
            ).handleDisposable()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    private fun Disposable.handleDisposable(): Disposable = apply { disposable.add(this) }
}