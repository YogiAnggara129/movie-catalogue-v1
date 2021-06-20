package com.anggasaraya.moviecatalogue.data.local.entity

data class TVShowEntity(
        val id: String,
        val dateReleased: String,
        val title: String,
        val genre: String? = null,
        val userScore: String? = null,
        val description: String,
        val status: String? = null,
        val language: String? = null,
        val imagePath: String
)