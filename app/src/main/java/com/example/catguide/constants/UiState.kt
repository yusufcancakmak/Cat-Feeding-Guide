package com.example.catguide.constants

sealed class UiState<out T>{
    //Loading, Succes, Fail
    object Loading:UiState<Nothing>()
    data class Success<out T>(val data :T):UiState<T>()
    data class Failure(val error:String?):UiState<Nothing>()
}