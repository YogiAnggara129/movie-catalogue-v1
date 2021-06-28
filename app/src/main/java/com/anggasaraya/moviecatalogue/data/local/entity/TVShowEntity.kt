package com.anggasaraya.moviecatalogue.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshow_entities")
data class TVShowEntity(
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

        @ColumnInfo(name = "image_path")
        val imagePath: String,

        @ColumnInfo(name = "is_favorite")
        var isFavorite: Boolean = false
)