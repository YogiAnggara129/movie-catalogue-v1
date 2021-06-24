package com.anggasaraya.moviecatalogue.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity

@Dao
interface CatalogueDao {

    @Query("SELECT * FROM movie_entities")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie_entities WHERE id = :idMovie")
    fun getMovieSelected(idMovie: String): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(module: MovieEntity)

    @Delete
    fun deleteMovie(module: MovieEntity)

    @Query("SELECT * FROM tvshow_entities")
    fun getTVShows(): LiveData<List<TVShowEntity>>

    @Query("SELECT * FROM movie_entities WHERE id = :idTVShow")
    fun getTVShowSelected(idTVShow: String): LiveData<TVShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTVShow(module: TVShowEntity)

    @Delete
    fun deleteTVShow(module: TVShowEntity)
}