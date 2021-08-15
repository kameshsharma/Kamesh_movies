package com.example.lowesmovies.models

data class Results(
    val display_title: String,
    val multimedia: Multimedia,
    val headline: String,
    val summary_short: String,
    val byline: String,
    val publication_date: String,
)

data class Multimedia(
    val src : String
)

data class MovieData(
    val results: List<Results>
)