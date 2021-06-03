package com.anggasaraya.moviecatalogue.data

import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity

interface CatalogueDataSource {
    fun getAllMovies(): List<MovieEntity>
    fun getAllTVShows(): List<TVShowEntity>
    fun getMovieSelected(id: String): MovieEntity
    fun getTVShowSelected(id: String): TVShowEntity
}