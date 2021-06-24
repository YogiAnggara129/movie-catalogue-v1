package com.anggasaraya.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.anggasaraya.moviecatalogue.data.CatalogueRepository
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity
import com.anggasaraya.moviecatalogue.helper.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVShowViewModelTest{
    private lateinit var viewModel: TVShowViewModel
    private val dummyTVShows = DataDummy.generateDummyTVShows()
    private val dummyTVShow = dummyTVShows[0]
    private val tvShowId = dummyTVShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<TVShowEntity>>

    @Before
    fun setUp() {
        viewModel = TVShowViewModel(catalogueRepository)
    }

    @Test
    fun getAllTVShows() {
        val tvShows = MutableLiveData<List<TVShowEntity>>()
        tvShows.value = dummyTVShows

        `when`(catalogueRepository.getAllTVShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getAllTVShows().value
        verify(catalogueRepository).getAllTVShows()
        Assert.assertNotNull(tvShowEntities)
        Assert.assertEquals(20, tvShowEntities?.size)

        viewModel.getAllTVShows().observeForever(observer)
        verify(observer).onChanged(dummyTVShows)
    }

    @Test
    fun getSelectedTVShow(){
        val tvShow = MutableLiveData<TVShowEntity>()
        tvShow.value = dummyTVShow

        `when`(catalogueRepository.getTVShowSelected(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getSelectedTVShow(tvShowId).value
        verify(catalogueRepository).getTVShowSelected(tvShowId)

        assertNotNull(tvShowEntity)
        assertEquals(tvShowEntity?.id, dummyTVShow.id)
        assertEquals(tvShowEntity?.dateReleased, dummyTVShow.dateReleased)
        assertEquals(tvShowEntity?.title, dummyTVShow.title)
        assertEquals(tvShowEntity?.genre, dummyTVShow.genre)
        assertEquals(tvShowEntity?.userScore, dummyTVShow.userScore)
        assertEquals(tvShowEntity?.description, dummyTVShow.description)
        assertEquals(tvShowEntity?.status, dummyTVShow.status)
        assertEquals(tvShowEntity?.language, dummyTVShow.language)
        assertEquals(tvShowEntity?.imagePath, dummyTVShow.imagePath)
    }
}