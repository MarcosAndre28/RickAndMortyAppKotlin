package com.example.rickandmortyappkotlin.data.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyappkotlin.utils.LoginState
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()

    val loginState = MutableLiveData<LoginState>()

    fun login(email: String, password: String) {
        loginState.value = LoginState.Loading
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Login bem-sucedido, atualizar o estado de login
                    loginState.value = LoginState.Success
                } else {
                    // Login falhou, atualizar o estado de login com a mensagem de erro
                    val message = task.exception?.message ?: "Ocorreu um erro durante o login"
                    loginState.value = LoginState.Error(message)
                }
            }
    }
}