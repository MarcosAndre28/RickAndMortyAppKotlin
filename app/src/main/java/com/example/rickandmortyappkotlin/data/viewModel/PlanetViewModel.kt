package com.example.rickandmortyappkotlin.data.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyappkotlin.data.model.Planet
import com.example.rickandmortyappkotlin.data.repository.PlanetRepository
import kotlinx.coroutines.launch

class PlanetViewModel(private val planetRepository: PlanetRepository) : ViewModel() {
    private val _planetInfos = MutableLiveData<List<Planet>>()
    val planetInfos: LiveData<List<Planet>> = _planetInfos

    fun getAllPlanetInfos() {
        viewModelScope.launch {
            _planetInfos.value = planetRepository.getAllPlanetInfos()
        }
    }
}
