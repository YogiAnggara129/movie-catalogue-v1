package com.anggasaraya.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.anggasaraya.moviecatalogue.data.CatalogueRepository

class MovieViewModel(private val mCatalogueRepository: CatalogueRepository) : ViewModel() {

    fun getSelectedMovie(id: String) = mCatalogueRepository.getMovieSelected(id)

    fun getAllMovies() = mCatalogueRepository.getAllMovies()
}