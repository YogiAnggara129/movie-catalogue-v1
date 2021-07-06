package com.anggasaraya.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.anggasaraya.moviecatalogue.data.local.LocalDataSource
import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity
import com.anggasaraya.moviecatalogue.data.remote.RemoteDataSource
import com.anggasaraya.moviecatalogue.helper.ApiDataDummy
import com.anggasaraya.moviecatalogue.helper.AppExecutors
import com.anggasaraya.moviecatalogue.helper.LiveDataTestUtil
import com.anggasaraya.moviecatalogue.helper.PagedListUtil
import com.anggasaraya.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CatalogueRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val catalogueRepository = FakeCatalogueRepository(remote, local, appExecutors)

    private val movieResponses = ApiDataDummy.generateRemoteDummyMovies()
    private val movieId = movieResponses[0]?.id.toString()
    private val tvShowResponses = ApiDataDummy.generateRemoteDummyTVShows()
    private val tvShowId = tvShowResponses[0]?.id.toString()

    @Test
    fun testGetAllMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        catalogueRepository.getAllMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(ApiDataDummy.generateDummyMovies()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun testGetMovieSelected() {
        val dummyEntity = MutableLiveData<MovieEntity>()
        dummyEntity.value = ApiDataDummy.generateDummyDetailMovie(movieId)
        `when`<LiveData<MovieEntity>>(local.getMovieSelected(movieId)).thenReturn(dummyEntity)

        val movieEntity = LiveDataTestUtil.getValue(catalogueRepository.getMovieSelected(movieId))
        verify(local).getMovieSelected(movieId)
        assertNotNull(movieEntity)
        assertNotNull(movieEntity.data)
        assertEquals(dummyEntity.value, movieEntity.data)
    }

    @Test
    fun testGetAllFavoriteMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllFavoriteMovies()).thenReturn(dataSourceFactory)
        catalogueRepository.getAllFavoriteMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(ApiDataDummy.generateDummyMovies()))
        verify(local).getAllFavoriteMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun testGetAllTVShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        `when`(local.getAllTVShows()).thenReturn(dataSourceFactory)
        catalogueRepository.getAllTVShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(ApiDataDummy.generateDummyTVShows()))
        verify(local).getAllTVShows()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun testGetTVShowSelected() {
        val dummyEntity = MutableLiveData<TVShowEntity>()
        dummyEntity.value = ApiDataDummy.generateDummyDetailTVShow(tvShowId)
        `when`<LiveData<TVShowEntity>>(local.getTVShowSelected(tvShowId)).thenReturn(dummyEntity)

        val tvShowEntity = LiveDataTestUtil.getValue(catalogueRepository.getTVShowSelected(tvShowId))
        verify(local).getTVShowSelected(tvShowId)
        assertNotNull(tvShowEntity)
        assertNotNull(tvShowEntity.data)
        assertEquals(dummyEntity.value, tvShowEntity.data)
    }

    @Test
    fun testGetAllFavoriteTVShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        `when`(local.getAllFavoriteTVShows()).thenReturn(dataSourceFactory)
        catalogueRepository.getAllFavoriteTVShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(ApiDataDummy.generateDummyTVShows()))
        verify(local).getAllFavoriteTVShows()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }
}