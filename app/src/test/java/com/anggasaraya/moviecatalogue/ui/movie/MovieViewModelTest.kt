package com.anggasaraya.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.anggasaraya.moviecatalogue.data.CatalogueRepository
import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.helper.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest{
    private lateinit var viewModel: MovieViewModel
    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyMovie = dummyMovies[0]
    private val movieId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogueRepository)
    }

    @Test
    fun getMovies() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(catalogueRepository.getAllMovies()).thenReturn(movies)
        val movieEntities = viewModel.getAllMovies().value
        verify(catalogueRepository).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(20, movieEntities?.size)

        viewModel.getAllMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getSelectedMovie(){
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        `when`(catalogueRepository.getMovieSelected(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getSelectedMovie(movieId).value
        verify(catalogueRepository).getMovieSelected(movieId)

        assertNotNull(movieEntity)
        assertEquals(movieEntity?.id.toString(), dummyMovie.id)
        assertEquals(movieEntity?.dateReleased.toString(), dummyMovie.dateReleased)
        assertEquals(movieEntity?.title.toString(), dummyMovie.title)
        assertEquals(movieEntity?.genre.toString(), dummyMovie.genre)
        assertEquals(movieEntity?.userScore.toString(), dummyMovie.userScore)
        assertEquals(movieEntity?.description.toString(), dummyMovie.description)
        assertEquals(movieEntity?.status.toString(), dummyMovie.status)
        assertEquals(movieEntity?.language.toString(), dummyMovie.language)
        assertEquals(movieEntity?.budget.toString(), dummyMovie.budget)
        assertEquals(movieEntity?.income.toString(), dummyMovie.income)
        assertEquals(movieEntity?.imagePath.toString(), dummyMovie.imagePath)
    }
}