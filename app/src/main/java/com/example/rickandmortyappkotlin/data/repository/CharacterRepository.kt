package com.example.rickandmortyappkotlin.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyappkotlin.data.api.Services
import com.example.rickandmortyappkotlin.data.model.Character

class CharacterRepository(private val api: Services) {

    private var currentPage = 1
    private var isLastPage = false

    val characterList = MutableLiveData<List<Character>>()

    suspend fun loadNextPage() {
        if (isLastPage) return

        val response = api.getCharacters(currentPage)

        if (response.isSuccessful) {
            val characters = response.body()?.results ?: emptyList()

            if (characters.isNotEmpty()) {
                characterList.postValue(characterList.value?.plus(characters) ?: characters)
                currentPage++
            } else {
                isLastPage = true
            }
        }
    }
}
