package com.anggasaraya.moviecatalogue.data.local

import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity
import com.anggasaraya.moviecatalogue.data.local.room.CatalogueDao

class LocalDataSource private constructor(private val mCatalogueDao: CatalogueDao){
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogueDao: CatalogueDao): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(catalogueDao)
            }
            return INSTANCE as LocalDataSource
        }
    }

    fun getAllFavoriteMovies() = mCatalogueDao.getMovies()

    fun getFavoriteMovieSelected(id: String) = mCatalogueDao.getMovieSelected(id)

    fun insertFavoriteMovie(movie: MovieEntity) = mCatalogueDao.insertMovie(movie)

    fun deleteFavoriteMovie(movie: MovieEntity) = mCatalogueDao.deleteMovie(movie)

    fun getAllFavoriteTVShows() = mCatalogueDao.getTVShows()

    fun getFavoriteTVShowSelected(id: String) = mCatalogueDao.getTVShowSelected(id)

    fun insertFavoriteTVShow(tvShow: TVShowEntity) = mCatalogueDao.insertTVShow(tvShow)

    fun deleteFavoriteTVShow(tvShow: TVShowEntity) = mCatalogueDao.deleteTVShow(tvShow)

}