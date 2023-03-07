package com.example.rickandmortyappkotlin.data.model



data class LocationList(
    val info: PageInfo,
    val results: List<Planet>
)

data class Planet(
    val name : String,
)
