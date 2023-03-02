package com.example.rickandmortyappkotlin.data.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyappkotlin.utils.RegisterState
import com.google.firebase.auth.FirebaseAuth

class RegisterViewModel : ViewModel() {

    private val _registerState = MutableLiveData<RegisterState>()
    val registerState : LiveData<RegisterState> get() = _registerState

    fun register(email : String, username : String, password : String){
        _registerState.value = RegisterState.Loading

        FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    val signInMethods = task.result?.signInMethods ?: emptyList()
                    if (signInMethods.isNotEmpty()){
                        _registerState.value = RegisterState.Error("Este email já foi cadastrado")
                    }
                    else{
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful){
                                    _registerState.value = RegisterState.Success
                                }
                                else {
                                    _registerState.value = RegisterState.Error(task.exception?.message)
                                }
                            }
                    }
                }
                else {
                    _registerState.value = RegisterState.Error(task.exception?.message)
                }
            }
    }


}