package com.pamella.moviesapp.ui.model

sealed class ViewState {
    object MovieNotFound : ViewState()
    object GeneralError : ViewState()
}
