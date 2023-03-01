package com.example.rickandmortyappkotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickandmortyappkotlin.database.dao.RegisterUserDao
import com.example.rickandmortyappkotlin.database.model.RegisterUser
import com.example.rickandmortyappkotlin.utils.Constants.Companion.DB_NAME

@Database(entities = [RegisterUser::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val registerUserDao : RegisterUserDao

    companion object{

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}