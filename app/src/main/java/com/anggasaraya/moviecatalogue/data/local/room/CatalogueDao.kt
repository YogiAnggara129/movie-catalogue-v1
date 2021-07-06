package com.anggasaraya.moviecatalogue.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity

@Dao
interface CatalogueDao {

    @Query("SELECT * FROM movie_entities")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movie_entities WHERE is_favorite = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movie_entities WHERE id = :idMovie")
    fun getMovieSelected(idMovie: String): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(module: List<MovieEntity>)

    @Update
    fun updateMovie(module: MovieEntity)

    @Query("SELECT * FROM tvshow_entities")
    fun getTVShows(): DataSource.Factory<Int, TVShowEntity>

    @Query("SELECT * FROM tvshow_entities WHERE is_favorite = 1")
    fun getFavoriteTVShows(): DataSource.Factory<Int, TVShowEntity>

    @Query("SELECT * FROM tvshow_entities WHERE id = :idTVShow")
    fun getTVShowSelected(idTVShow: String): LiveData<TVShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTVShow(module: List<TVShowEntity>)

    @Update
    fun updateTVShow(module: TVShowEntity)
}