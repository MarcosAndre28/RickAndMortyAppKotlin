package com.example.rickandmortyappkotlin.data.model

import java.io.Serializable

class EpisodesResponse(
    val info: Info,
    val results: List<Episode>
)

class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

class Episode(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    var characters: List<String>,
    var characterNames: String = "",
    var isExpanded: Boolean = false,
    val url: String,
    val created: String
) : Serializable