package com.anggasaraya.moviecatalogue.ui.movie

import com.anggasaraya.moviecatalogue.helper.DataDummy
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieViewModelTest{
    private lateinit var viewModel: MovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovies() {
        val movieEntities = viewModel.getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities.size)
    }

    @Test
    fun getSelectedMovie(){
        viewModel.setSelectedMovie(movieId)
        val movieEntity = viewModel.getSelectedMovie()
        assertNotNull(movieEntity)
        assertEquals(movieEntity.id, dummyMovie.id)
        assertEquals(movieEntity.dateReleased, dummyMovie.dateReleased)
        assertEquals(movieEntity.title, dummyMovie.title)
        assertEquals(movieEntity.genre, dummyMovie.genre)
        assertEquals(movieEntity.userScore, dummyMovie.userScore)
        assertEquals(movieEntity.description, dummyMovie.description)
        assertEquals(movieEntity.status, dummyMovie.status)
        assertEquals(movieEntity.language, dummyMovie.language)
        assertEquals(movieEntity.budget, dummyMovie.budget)
        assertEquals(movieEntity.income, dummyMovie.income)
        assertEquals(movieEntity.imagePath, dummyMovie.imagePath)
    }
}