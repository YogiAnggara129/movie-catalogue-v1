package com.anggasaraya.moviecatalogue.ui.tvshow

import com.anggasaraya.moviecatalogue.helper.DataDummy
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test

class TVShowViewModelTest{
    private lateinit var viewModel: TVShowViewModel
    private val dummyTVShow = DataDummy.generateDummyTVShows()[0]
    private val tvShowsId = dummyTVShow.id

    @Before
    fun setUp() {
        viewModel = TVShowViewModel()
    }

    @Test
    fun getAllTVShows() {
        val movieEntities = viewModel.getAllTVShows()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities.size)
    }

    @Test
    fun getSelectedTVShow(){
        viewModel.setSelectedTVShow(tvShowsId)
        val tvShowEntity = viewModel.getSelectedTVShow()
        assertNotNull(tvShowEntity)
        assertEquals(tvShowEntity.id, dummyTVShow.id)
        assertEquals(tvShowEntity.dateReleased, dummyTVShow.dateReleased)
        assertEquals(tvShowEntity.title, dummyTVShow.title)
        assertEquals(tvShowEntity.genre, dummyTVShow.genre)
        assertEquals(tvShowEntity.userScore, dummyTVShow.userScore)
        assertEquals(tvShowEntity.description, dummyTVShow.description)
        assertEquals(tvShowEntity.status, dummyTVShow.status)
        assertEquals(tvShowEntity.language, dummyTVShow.language)
        assertEquals(tvShowEntity.imagePath, dummyTVShow.imagePath)
    }
}