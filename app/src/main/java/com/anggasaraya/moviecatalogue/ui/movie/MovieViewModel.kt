package com.anggasaraya.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anggasaraya.moviecatalogue.data.CatalogueRepository
import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.data.remote.response.ResultsItem
import com.anggasaraya.moviecatalogue.helper.DataDummy

class MovieViewModel(private val mCatalogueRepository: CatalogueRepository) : ViewModel() {
    private lateinit var id: String

    fun setSelectedMovie(id: String){
        this.id = id
    }

    fun getSelectedMovie(): MovieEntity = mCatalogueRepository.getMovieSelected(id)

    fun getAllMovies(): LiveData<List<MovieEntity>> = mCatalogueRepository.getAllMovies()

}