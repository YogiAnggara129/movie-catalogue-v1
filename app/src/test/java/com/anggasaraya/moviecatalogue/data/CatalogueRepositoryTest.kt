package com.anggasaraya.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anggasaraya.moviecatalogue.data.remote.RemoteDataSource
import com.anggasaraya.moviecatalogue.helper.DataDummy
import com.anggasaraya.moviecatalogue.helper.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class CatalogueRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val catalogueRepository = FakeCatalogueRepository(remote)

    private val moviesResponse = DataDummy.generateDummyMoviesResponse()
    private val movieDetail = DataDummy.generateDummyDetailMovieResponse()
    private val movieId = moviesResponse[0].id.toString()
    private val tvShowResponse = DataDummy.generateDummyTVShowsResponse()
    private val tvShowDetail = DataDummy.generateDummyDetailTVShowResponse()
    private val tvShowId = tvShowResponse[0].id.toString()

    @Test
    fun testGetAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                    .onAllMoviesReceived(moviesResponse)
            null
        }.`when`(remote).getAllMovies(any())

        val movieEntities = LiveDataTestUtil.getValue(catalogueRepository.getAllMovies())
        verify(remote).getAllMovies(any())
        assertNotNull(movieEntities)
        assertEquals(moviesResponse.size, movieEntities.size)
    }

    @Test
    fun testGetMovieSelected() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMovieSelectedCallback).onMovieSelectedReceived(movieDetail)
            null
        }.`when`(remote).getMovieSelected(any(), eq(movieId))

        val movieDetailEntity = LiveDataTestUtil.getValue(catalogueRepository.getMovieSelected(movieId))
        verify(remote).getMovieSelected(any(), eq(movieId))
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetail.id.toString(), movieDetailEntity.id)
    }

    @Test
    fun testGetAllTVShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTVShowsCallback)
                    .onAllTVShowsReceived(tvShowResponse)
            null
        }.`when`(remote).getAllTVShows(any())

        val tvShowEntities = LiveDataTestUtil.getValue(catalogueRepository.getAllTVShows())
        verify(remote).getAllTVShows(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size, tvShowEntities.size)
    }

    @Test
    fun testGetTVShowSelected() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTVShowSelectedCallback).onTVShowsSelectedReceived(tvShowDetail)
            null
        }.`when`(remote).getTVShowSelected(any(), eq(tvShowId))

        val tvShowDetailEntity = LiveDataTestUtil.getValue(catalogueRepository.getTVShowSelected(tvShowId))
        verify(remote).getTVShowSelected(any(), eq(tvShowId))
        assertNotNull(tvShowDetailEntity)
        assertEquals(tvShowDetail.id.toString(), tvShowDetailEntity.id)
    }
}