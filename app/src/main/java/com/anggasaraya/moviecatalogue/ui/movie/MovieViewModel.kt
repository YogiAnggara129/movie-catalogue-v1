package com.anggasaraya.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.anggasaraya.moviecatalogue.data.MovieEntity
import com.anggasaraya.moviecatalogue.helper.DataDummy

class MovieViewModel : ViewModel() {
    private lateinit var id: String

    fun setSelectedMovie(id: String){
        this.id = id
    }

    fun getSelectedMovie(): MovieEntity{
        lateinit var movie: MovieEntity
        val movieEntities = DataDummy.generateDummyMovies()
        for (movieEntity in movieEntities) {
            if (movieEntity.id == id) {
                movie = movieEntity
            }
        }
        return movie
    }

    fun getAllMovies(): ArrayList<MovieEntity> = DataDummy.generateDummyMovies()

}