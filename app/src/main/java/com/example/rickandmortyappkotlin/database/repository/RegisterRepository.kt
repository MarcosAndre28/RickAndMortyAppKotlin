package com.example.rickandmortyappkotlin.database.repository

import com.example.rickandmortyappkotlin.database.dao.RegisterUserDao
import com.example.rickandmortyappkotlin.database.model.RegisterUser

class RegisterRepository(private val dao : RegisterUserDao) {

    val users = dao.getAllUsers()

    suspend fun insert(user : RegisterUser){
        return dao.insert(user)
    }

    suspend fun getUserName(userName: String): RegisterUser{
        return dao.getUsername(userName)
    }

    suspend fun update(registerUser: RegisterUser){
        return dao.update(registerUser)
    }
}