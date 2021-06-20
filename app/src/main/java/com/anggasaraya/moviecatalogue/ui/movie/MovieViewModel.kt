package com.anggasaraya.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anggasaraya.moviecatalogue.data.CatalogueRepository
import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.data.remote.response.ResultsItem

class MovieViewModel(private val mCatalogueRepository: CatalogueRepository) : ViewModel() {

    fun getSelectedMovie(id: String) = mCatalogueRepository.getMovieSelected(id)

    fun getAllMovies() = mCatalogueRepository.getAllMovies()
}