package com.example.rickandmortyappkotlin.utils

sealed class LoginState {
    object Loading : LoginState()
    object Success : LoginState()
    class Error(val message: String) : LoginState()
}

