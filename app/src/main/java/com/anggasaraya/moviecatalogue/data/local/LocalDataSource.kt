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

    fun getAllMovies() = mCatalogueDao.getMovies()

    fun getAllFavoriteMovies() = mCatalogueDao.getFavoriteMovies()

    fun getFavoriteMovieSelected(id: String) = mCatalogueDao.getMovieSelected(id)

    fun insertMovies(movies: List<MovieEntity>) = mCatalogueDao.insertMovie(movies)

    fun deleteFavoriteMovie(movie: MovieEntity) = mCatalogueDao.deleteMovie(movie)

    fun updateMovieDetail(movie: MovieEntity) = mCatalogueDao.updateMovie(movie)

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mCatalogueDao.updateMovie(movie)
    }

    fun getAllTVShows() = mCatalogueDao.getTVShows()

    fun getAllFavoriteTVShows() = mCatalogueDao.getFavoriteTVShows()

    fun getFavoriteTVShowSelected(id: String) = mCatalogueDao.getTVShowSelected(id)

    fun insertTVShow(tvShows: List<TVShowEntity>) = mCatalogueDao.insertTVShow(tvShows)

    fun deleteFavoriteTVShow(tvShow: TVShowEntity) = mCatalogueDao.deleteTVShow(tvShow)

    fun updateTVShowDetail(tvShow: TVShowEntity) = mCatalogueDao.updateTVShow(tvShow)

    fun setTVShowFavorite(tvShow: TVShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        mCatalogueDao.updateTVShow(tvShow)
    }
}