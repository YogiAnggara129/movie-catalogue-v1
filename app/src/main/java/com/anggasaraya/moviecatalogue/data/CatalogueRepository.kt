package com.anggasaraya.moviecatalogue.data

import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity
import com.anggasaraya.moviecatalogue.helper.DataDummy

class CatalogueRepository private constructor() : CatalogueDataSource{

    companion object{
        @Volatile
        private var instance: CatalogueRepository? = null

        fun getInstance(): CatalogueRepository =
                instance ?: synchronized(this){
                    instance ?: CatalogueRepository()
                }
    }

    override fun getAllMovies(): ArrayList<MovieEntity> {
        return DataDummy.generateDummyMovies()
    }

    override fun getAllTVShows(): ArrayList<TVShowEntity> {
        return DataDummy.generateDummyTVShows()
    }

    override fun getMovieSelected(id: String): MovieEntity {
        lateinit var movie: MovieEntity
        val movieEntities = DataDummy.generateDummyMovies()
        for (movieEntity in movieEntities) {
            if (movieEntity.id == id) {
                movie = movieEntity
            }
        }
        return movie
    }

    override fun getTVShowSelected(id: String): TVShowEntity {
        lateinit var tvShow: TVShowEntity
        val tvShowEntities = DataDummy.generateDummyTVShows()
        for (tvShowEntity in tvShowEntities) {
            if (tvShowEntity.id == id) {
                tvShow = tvShowEntity
            }
        }
        return tvShow
    }
}