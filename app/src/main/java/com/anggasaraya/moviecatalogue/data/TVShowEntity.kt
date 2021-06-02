package com.anggasaraya.moviecatalogue.data

data class TVShowEntity (
    val id: String,
    val dateReleased: String,
    val title: String,
    val genre: String,
    val userScore: String,
    val description: String,
    val status: String,
    val language: String,
    val imagePath: Int
)