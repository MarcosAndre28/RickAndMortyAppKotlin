package com.example.rickandmortyappkotlin.data.repository

import com.example.rickandmortyappkotlin.data.api.RetrofitInstance
import com.example.rickandmortyappkotlin.data.model.Episode
import com.google.android.gms.common.api.ApiException

class EpisodesRepository {

    suspend fun getEpisodesBySeason(season: Int, page: Int): List<Episode> {
        val episodesList = mutableListOf<Episode>()
        var currentPage = page
        var totalPages = 1
        while (currentPage <= totalPages) {
            val response = RetrofitInstance.api.getEpisodesBySeason(season, currentPage)
            if (response.isSuccessful) {
                val episodesResponse = response.body()
                episodesResponse?.let {
                    episodesList.addAll(it.results)
                    totalPages = it.info.pages
                    currentPage++
                }
            }
        }
        return episodesList
    }
}

