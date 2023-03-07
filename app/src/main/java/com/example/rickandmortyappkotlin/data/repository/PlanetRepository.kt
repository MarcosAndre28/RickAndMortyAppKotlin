package com.example.rickandmortyappkotlin.data.repository

import com.example.rickandmortyappkotlin.data.api.RetrofitInstance
import com.example.rickandmortyappkotlin.data.model.Planet

class PlanetRepository() {
    suspend fun getAllPlanetInfos() : List<Planet>{
        val locationList = RetrofitInstance.api.getAllPlanet()
        return locationList.results.map { location ->
            Planet(name = location.name)
        }
    }
}