package com.anggasaraya.moviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import com.anggasaraya.moviecatalogue.data.CatalogueRepository

class TVShowViewModel(private val mCatalogueRepository: CatalogueRepository) : ViewModel() {

    fun getSelectedTVShow(id: String) = mCatalogueRepository.getTVShowSelected(id)
    
    fun getAllTVShows() = mCatalogueRepository.getAllTVShows()
}