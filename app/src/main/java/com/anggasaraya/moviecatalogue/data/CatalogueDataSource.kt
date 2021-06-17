package com.anggasaraya.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity
import com.anggasaraya.moviecatalogue.data.remote.response.ResultsItem

interface CatalogueDataSource {
    fun getAllMovies(): LiveData<List<MovieEntity>>
    fun getAllTVShows(): List<TVShowEntity>
    fun getMovieSelected(id: String): MovieEntity
    fun getTVShowSelected(id: String): TVShowEntity
}