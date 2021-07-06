package com.anggasaraya.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity
import com.anggasaraya.moviecatalogue.vo.Resource

interface CatalogueDataSource {
    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getMovieSelected(id: String): LiveData<Resource<MovieEntity>>
    fun setMovieFavorite(movie: MovieEntity, state: Boolean)
    fun getAllFavoriteMovies(): LiveData<PagedList<MovieEntity>>
    fun getAllTVShows(): LiveData<Resource<PagedList<TVShowEntity>>>
    fun getTVShowSelected(id: String): LiveData<Resource<TVShowEntity>>
    fun setTVShowFavorite(tvShow: TVShowEntity, state: Boolean)
    fun getAllFavoriteTVShows(): LiveData<PagedList<TVShowEntity>>
}