package com.example.rickandmortyappkotlin.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyappkotlin.data.repository.CharacterRepository

class CharacterViewModelFactory(private val repository: CharacterRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterViewModel(repository) as T
    }
}
