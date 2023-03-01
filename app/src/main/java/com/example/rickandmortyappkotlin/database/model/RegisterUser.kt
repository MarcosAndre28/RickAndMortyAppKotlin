package com.example.rickandmortyappkotlin.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmortyappkotlin.utils.Constants.Companion.TABLE_REGISTER_USER

@Entity(tableName = TABLE_REGISTER_USER)
data class RegisterUser(

    @PrimaryKey(autoGenerate = true)
    var userId : Long,

    @ColumnInfo(name = "email")
    var email : String,

    @ColumnInfo(name = "password")
    var password : String
)
