package com.example.rickandmortyappkotlin.data.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.rickandmortyappkotlin.data.api.Services
import com.example.rickandmortyappkotlin.data.model.CharacterList
import com.example.rickandmortyappkotlin.data.repository.CharacterRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CharacterViewModel(private val repository: CharacterRepository) : ViewModel() {

    val characterList = repository.characterList

    suspend fun loadNextPage() {
        repository.loadNextPage()
    }
}
