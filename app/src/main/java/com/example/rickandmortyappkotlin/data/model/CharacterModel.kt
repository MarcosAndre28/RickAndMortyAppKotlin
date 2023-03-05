package com.example.rickandmortyappkotlin.data.model

import java.io.Serializable


data class CharacterList(
    var results : List<CharacterData>
)
data class CharacterData(
    var id : Int,
    var name : String,
    var status : String,
    var species : String,
    var gender : String,
    var origin: LocationData,
    var location : LocationData,
    var image : String,
    var episode : List<String>
) : Serializable
