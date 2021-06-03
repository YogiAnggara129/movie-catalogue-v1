package com.anggasaraya.moviecatalogue.data.local.entity

data class MovieEntity (
    val id: String,
    val dateReleased: String,
    val title: String,
    val genre: String,
    val userScore: String,
    val description: String,
    val status: String,
    val language: String,
    val budget: String,
    val income: String,
    val imagePath: Int
)