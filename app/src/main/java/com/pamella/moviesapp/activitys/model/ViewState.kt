package com.pamella.moviesapp.activitys.model

sealed class ViewState {
    object MovieNotFound : ViewState()
    object GeneralError : ViewState()
}
