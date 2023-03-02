package com.example.rickandmortyappkotlin.utils

sealed class RegisterState {
    object Loading : RegisterState()
    object Success : RegisterState()
    data class Error(val message : String?) : RegisterState()
}
