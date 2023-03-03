package com.example.rickandmortyappkotlin.data.api

import com.example.rickandmortyappkotlin.data.model.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {
        @GET("character")
        suspend fun getCharacters(@Query("page") page : Int): Response<CharacterList>
}