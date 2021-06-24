package com.anggasaraya.moviecatalogue.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entities")
data class MovieEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "data_released")
    val dateReleased: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "genre")
    val genre: String? = null,

    @ColumnInfo(name = "user_score")
    val userScore: String? = null,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "status")
    val status: String? = null,

    @ColumnInfo(name = "language")
    val language: String? = null,

    @ColumnInfo(name = "budget")
    val budget: String? = null,

    @ColumnInfo(name = "income")
    val income: String? = null,

    @ColumnInfo(name = "image_path")
    val imagePath: String
)