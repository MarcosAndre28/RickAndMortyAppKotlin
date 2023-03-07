package com.example.rickandmortyappkotlin.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyappkotlin.data.repository.CharacterRepository
import com.example.rickandmortyappkotlin.data.repository.PlanetRepository

class PlanetViewModelFactory(private val repository: PlanetRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlanetViewModel(repository) as T
    }
}
