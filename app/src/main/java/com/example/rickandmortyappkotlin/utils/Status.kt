package com.example.rickandmortyappkotlin.utils

sealed class Status<out T> {
    data class Success<out T>(val data: T) : Status<T>()
    data class Error(val exception: String) : Status<Nothing>()
    object Loading : Status<Nothing>()
}
