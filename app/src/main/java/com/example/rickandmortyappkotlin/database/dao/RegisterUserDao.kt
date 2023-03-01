package com.example.rickandmortyappkotlin.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.rickandmortyappkotlin.database.model.RegisterUser

@Dao
interface RegisterUserDao {

    @Insert
    suspend fun insert(registerUser: RegisterUser)

    @Query("SELECT * FROM register_user ORDER BY userId DESC")
    fun getAllUsers() : LiveData<List<RegisterUser>>

    @Query("SELECT * FROM register_user WHERE email LIKE :email")
    suspend fun getUsername(email: String): RegisterUser

    @Update
    suspend fun update(registerUser: RegisterUser)
}