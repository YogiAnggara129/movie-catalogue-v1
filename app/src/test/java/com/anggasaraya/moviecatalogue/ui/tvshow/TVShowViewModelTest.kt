package com.anggasaraya.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.anggasaraya.moviecatalogue.data.CatalogueRepository
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity
import com.anggasaraya.moviecatalogue.helper.ApiDataDummy
import com.anggasaraya.moviecatalogue.vo.Resource
import org.junit.Assert
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class TVShowViewModelTest{
    private lateinit var viewModel: TVShowViewModel
    private val dataDummies = ApiDataDummy.generateDummyTVShows()
    private val id = dataDummies[0].id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TVShowEntity>>>

    @Mock
    private lateinit var observerWithoutPagedList: Observer<Resource<TVShowEntity>>

    @Mock
    private lateinit var observerWithoutResource: Observer<PagedList<TVShowEntity>>


    @Before
    fun setUp() {
        viewModel = TVShowViewModel(catalogueRepository)
    }

    @Test
    fun `getAllTVShows should be success`() {
        val tvShows = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<TVShowEntity>>>()
        expected.value = Resource.success(tvShows)

        `when`(catalogueRepository.getAllTVShows()).thenReturn(expected)

        viewModel.getAllTVShows().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)
        Mockito.verify(catalogueRepository).getAllTVShows()

        val expectedValue = expected.value
        val actualValue = viewModel.getAllTVShows().value
        Assert.assertEquals(expectedValue, actualValue)
        Assert.assertEquals(expectedValue?.data, actualValue?.data)
        Assert.assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun `getAllTVShows should be success but data is empty`() {
        val tvShows = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<TVShowEntity>>>()
        expected.value = Resource.success(tvShows)

        `when`(catalogueRepository.getAllTVShows()).thenReturn(expected)

        viewModel.getAllTVShows().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)
        Mockito.verify(catalogueRepository).getAllTVShows()

        val actualValueDataSize = viewModel.getAllTVShows().value?.data?.size
        assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)
    }

    @Test
    fun `getAllTVShows should be error`() {
        val expectedMessage = "Something happen dude!"
        val expected = MutableLiveData<Resource<PagedList<TVShowEntity>>>()
        expected.value = Resource.error(expectedMessage, null)

        `when`(catalogueRepository.getAllTVShows()).thenReturn(expected)

        viewModel.getAllTVShows().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)
        Mockito.verify(catalogueRepository).getAllTVShows()

        val actualMessage = viewModel.getAllTVShows().value?.message
        assertEquals(expectedMessage, actualMessage)
    }

    @Test
    fun `setSelectedId should be success`() {
        val expected = MutableLiveData<Resource<TVShowEntity>>()
        expected.value = Resource.success(ApiDataDummy.generateDummyDetailTVShow(id))

        `when`(catalogueRepository.getTVShowSelected(id)).thenReturn(expected)

        viewModel.setSelectedId(id)
        viewModel.tvShowData.observeForever(observerWithoutPagedList)

        Mockito.verify(observerWithoutPagedList).onChanged(expected.value)
        Mockito.verify(catalogueRepository).getTVShowSelected(id)

        val expectedValue = expected.value
        val actualValue = viewModel.tvShowData.value

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `setFavorite should be success trigger TVShowEntity observer`() {
        val expected = MutableLiveData<Resource<TVShowEntity>>()
        expected.value = Resource.success(ApiDataDummy.generateDummyDetailTVShow(id, true))

        `when`(catalogueRepository.getTVShowSelected(id)).thenReturn(expected)

        viewModel.setSelectedId(id)
        viewModel.setFavorite()
        viewModel.tvShowData.observeForever(observerWithoutPagedList)

        Mockito.verify(observerWithoutPagedList).onChanged(expected.value)
        Mockito.verify(catalogueRepository).getTVShowSelected(id)

        val expectedValue = expected.value
        val actualValue = viewModel.tvShowData.value

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `getAllFavoriteTVShows should be success`() {
        val expected = MutableLiveData<PagedList<TVShowEntity>>()
        expected.value = PagedTestDataSources.snapshot(dataDummies)

        `when`(catalogueRepository.getAllFavoriteTVShows()).thenReturn(expected)

        viewModel.setFavorite()
        viewModel.getAllFavoriteTVShows().observeForever(observerWithoutResource)
        Mockito.verify(observerWithoutResource).onChanged(expected.value)
        Mockito.verify(catalogueRepository).getAllFavoriteTVShows()

        val expectedValue = expected.value
        val actualValue = viewModel.getAllFavoriteTVShows().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun `getAllFavoriteTVShows should be success but data is empty`() {
        val expected = MutableLiveData<PagedList<TVShowEntity>>()
        expected.value = PagedTestDataSources.snapshot()

        `when`(catalogueRepository.getAllFavoriteTVShows()).thenReturn(expected)

        viewModel.getAllFavoriteTVShows().observeForever(observerWithoutResource)
        Mockito.verify(observerWithoutResource).onChanged(expected.value)
        Mockito.verify(catalogueRepository).getAllFavoriteTVShows()

        val actualValueDataSize = viewModel.getAllFavoriteTVShows().value?.size
        assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)
    }

    class PagedTestDataSources private constructor(private val items: List<TVShowEntity>) : PositionalDataSource<TVShowEntity>() {
        companion object {
            fun snapshot(items: List<TVShowEntity> = listOf()): PagedList<TVShowEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                        .setNotifyExecutor(Executors.newSingleThreadExecutor())
                        .setFetchExecutor(Executors.newSingleThreadExecutor())
                        .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<TVShowEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TVShowEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}