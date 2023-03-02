package com.example.rickandmortyappkotlin.data.model


data class CharacterList(
    var results : List<Character>
)

data class Character(
    var id : Int,
    var name : String,
    var status : String,
    var species : String,
    var gender : String,
    var origin: LocationData,
    var location : LocationData,
    var image : String,
    var spisode : List<String>
)
