package com.example.rickandmortyappkotlin.data.repository

import com.example.rickandmortyappkotlin.data.api.RetrofitInstance
import com.example.rickandmortyappkotlin.data.model.CharacterList
import retrofit2.Response

class CharacterRepository() {

    suspend fun getCharacters(page: Int): CharacterList {
        return RetrofitInstance.api.getCharacters(page)
    }

    suspend fun getCharactersByName(name: String): CharacterList{
        return RetrofitInstance.api.getCharactersByName(name)
    }

    suspend fun getCharactersbyStatusAndGender(status : String, gender: String, page:Int): CharacterList{
        return RetrofitInstance.api.getCharactersbyStatusAndGender(status, gender, page)
    }

    suspend fun getCharactersByStatus(status : String, page:Int): CharacterList{
        return RetrofitInstance.api.getCharactersByStatus(status, page)
    }

    suspend fun getCharactersByGender(gender : String, page:Int): CharacterList{
        return RetrofitInstance.api.getCharactersByGender(gender, page)
    }
}
