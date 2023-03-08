package com.example.rickandmortyappkotlin.data.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyappkotlin.data.model.Episode
import com.example.rickandmortyappkotlin.data.repository.EpisodesRepository
import kotlinx.coroutines.launch

class EpisodesViewModel(private val episodesRepository: EpisodesRepository) : ViewModel() {

    private val episodesList = MutableLiveData<List<Episode>>()

    fun getEpisodesBySeason(season: Int, page: Int) {
        viewModelScope.launch {
            val episodes = episodesRepository.getEpisodesBySeason(season, page)
            episodesList.postValue(episodes)
        }
    }

    fun getEpisodesList(): LiveData<List<Episode>> {
        return episodesList
    }
}
