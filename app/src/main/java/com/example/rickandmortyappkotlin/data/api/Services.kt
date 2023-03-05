package com.example.rickandmortyappkotlin.data.api

import com.example.rickandmortyappkotlin.data.model.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {
        @GET("character")
        suspend fun getCharacters(@Query("page") page : Int): CharacterList

        @GET("character")
        suspend fun getCharactersByName(@Query("name") name : String): CharacterList

        @GET("character")
        suspend fun getCharactersbyStatusAndGender(@Query("status") status : String,
                                                   @Query("gender") gender : String,
                                                   @Query("page") page : Int): CharacterList

        @GET("character")
        suspend fun getCharactersByStatus(@Query("status") status : String, @Query("page") page : Int): CharacterList

        @GET("character")
        suspend fun getCharactersByGender( @Query("gender") gender : String, @Query("page") page : Int): CharacterList
}