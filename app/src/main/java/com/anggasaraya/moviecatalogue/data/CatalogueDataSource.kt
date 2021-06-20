package com.anggasaraya.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity
import com.anggasaraya.moviecatalogue.data.remote.response.ResultsItem

interface CatalogueDataSource {
    fun getAllMovies(): LiveData<List<MovieEntity>>
    fun getMovieSelected(id: String): LiveData<MovieEntity>
    fun getAllTVShows(): LiveData<List<TVShowEntity>>
    fun getTVShowSelected(id: String): LiveData<TVShowEntity>
}