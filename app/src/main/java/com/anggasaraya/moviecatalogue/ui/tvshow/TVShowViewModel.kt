package com.anggasaraya.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.anggasaraya.moviecatalogue.data.CatalogueRepository
import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity
import com.anggasaraya.moviecatalogue.vo.Resource

class TVShowViewModel(private val mCatalogueRepository: CatalogueRepository) : ViewModel() {
    private val tvShowId = MutableLiveData<String>()
    private val _isFavorite = MutableLiveData<Boolean>()
    fun isFavorite() : LiveData<Boolean> {
        _isFavorite.value = (tvShowData.value?.data?.isFavorite)
        return _isFavorite
    }

    fun setSelectedId(movieId: String) {
        this.tvShowId.value = movieId
    }

    var tvShowData: LiveData<Resource<TVShowEntity>> = Transformations.switchMap(tvShowId) { mTVShowId ->
        mCatalogueRepository.getTVShowSelected(mTVShowId)
    }

    fun getSelectedTVShow(id: String) = mCatalogueRepository.getTVShowSelected(id)

    fun getAllTVShows() : LiveData<Resource<PagedList<TVShowEntity>>> = mCatalogueRepository.getAllTVShows()

    fun getAllFavoriteTVShows() = mCatalogueRepository.getAllFavoriteTVShows()

    fun setFavorite() {
        val tvShowResource = tvShowData.value
        if (tvShowResource != null) {
            val tvShowEntity = tvShowResource.data
            if (tvShowEntity != null){
                val newState = !tvShowEntity.isFavorite
                mCatalogueRepository.setTVShowFavorite(tvShowEntity, newState)
            }
        }
    }
}