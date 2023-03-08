package com.example.rickandmortyappkotlin.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyappkotlin.data.repository.CharacterRepository
import com.example.rickandmortyappkotlin.data.repository.EpisodesRepository

class EpisodeViewModelFactory(private val repository: EpisodesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodesViewModel(repository) as T
    }
}
