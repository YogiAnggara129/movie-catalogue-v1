package com.anggasaraya.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.anggasaraya.moviecatalogue.data.CatalogueRepository
import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.vo.Resource

class MovieViewModel(private val mCatalogueRepository: CatalogueRepository) : ViewModel() {
    private val movieId = MutableLiveData<String>()
    private val _isFavorite = MutableLiveData<Boolean>()
    fun isFavorite() : LiveData<Boolean> {
        _isFavorite.value = movieData.value?.data?.isFavorite
        return _isFavorite
    }

    fun setSelectedId(movieId: String) {
        this.movieId.value = movieId
    }

    var movieData: LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId) { mMovieId ->
        mCatalogueRepository.getMovieSelected(mMovieId)
    }

    fun getAllMovies() : LiveData<Resource<PagedList<MovieEntity>>> = mCatalogueRepository.getAllMovies()

    fun getAllFavoriteMovies() = mCatalogueRepository.getAllFavoriteMovies()

    fun setFavorite() {
        val movieResource = movieData.value
        if (movieResource != null) {
            val movieEntity = movieResource.data
            if (movieEntity != null){
                val newState = !movieEntity.isFavorite
                mCatalogueRepository.setMovieFavorite(movieEntity, newState)
            }
        }
    }
}