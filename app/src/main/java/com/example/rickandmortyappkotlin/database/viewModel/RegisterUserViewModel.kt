package com.example.rickandmortyappkotlin.database.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyappkotlin.database.AppDatabase
import com.example.rickandmortyappkotlin.database.model.RegisterUser
import com.example.rickandmortyappkotlin.database.repository.RegisterRepository
import com.example.rickandmortyappkotlin.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RegisterUserViewModel (application: Application) : AndroidViewModel(application){

    var repository : RegisterRepository

    init {
        val registerDao = AppDatabase.getInstance(application).registerUserDao
        repository =RegisterRepository(registerDao)
    }

    fun insert(registerUser: RegisterUser): Flow<Status<Unit>> = flow{
        emit(Status.Loading)

        try {
            withContext(Dispatchers.IO){
                repository.insert(registerUser)
            }
            emit(Status.Success(Unit))
        }
        catch (e: java.lang.Exception){
            emit(Status.Error(e.message ?: "Error occurred"))
        }
    }.flowOn(Dispatchers.Default)


    fun update(registerUser: RegisterUser) = viewModelScope.launch {
        repository.update(registerUser)
    }

}

