package com.anggasaraya.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.anggasaraya.moviecatalogue.data.CatalogueRepository
import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.helper.ApiDataDummy
import com.anggasaraya.moviecatalogue.vo.Resource
import org.junit.Assert.assertEquals
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
class MovieViewModelTest{
    private lateinit var viewModel: MovieViewModel
    private val dataDummies = ApiDataDummy.generateDummyMovies()
    private val id = dataDummies[0].id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var observerWithoutPagedList: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var observerWithoutResource: Observer<PagedList<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogueRepository)
    }

    @Test
    fun `getAllMovies should be success`() {
        val movies = PagedTestDataSources.snapshot(ApiDataDummy.generateDummyMovies())
        val expected = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        expected.value = Resource.success(movies)

        `when`(catalogueRepository.getAllMovies()).thenReturn(expected)

        viewModel.getAllMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)
        Mockito.verify(catalogueRepository).getAllMovies()

        val expectedValue = expected.value
        val actualValue = viewModel.getAllMovies().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun `getAllMovies should be success but data is empty`() {
        val movies = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        expected.value = Resource.success(movies)

        `when`(catalogueRepository.getAllMovies()).thenReturn(expected)

        viewModel.getAllMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)
        Mockito.verify(catalogueRepository).getAllMovies()

        val actualValueDataSize = viewModel.getAllMovies().value?.data?.size
        assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)
    }

    @Test
    fun `getAllMovies should be error`() {
        val expectedMessage = "Something happen dude!"
        val expected = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        expected.value = Resource.error(expectedMessage, null)

        `when`(catalogueRepository.getAllMovies()).thenReturn(expected)

        viewModel.getAllMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)
        Mockito.verify(catalogueRepository).getAllMovies()

        val actualMessage = viewModel.getAllMovies().value?.message
        assertEquals(expectedMessage, actualMessage)
    }

    @Test
    fun `setSelectedId should be success`() {
        val expected = MutableLiveData<Resource<MovieEntity>>()
        expected.value = Resource.success(ApiDataDummy.generateDummyDetailMovie(id))

        `when`(catalogueRepository.getMovieSelected(id)).thenReturn(expected)

        viewModel.setSelectedId(id)
        viewModel.movieData.observeForever(observerWithoutPagedList)

        Mockito.verify(observerWithoutPagedList).onChanged(expected.value)
        Mockito.verify(catalogueRepository).getMovieSelected(id)

        val expectedValue = expected.value
        val actualValue = viewModel.movieData.value

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `setFavorite should be success trigger MovieEntity observer`() {
        val expected = MutableLiveData<Resource<MovieEntity>>()
        expected.value = Resource.success(ApiDataDummy.generateDummyDetailMovie(id, true))

        `when`(catalogueRepository.getMovieSelected(id)).thenReturn(expected)

        viewModel.setSelectedId(id)
        viewModel.setFavorite()
        viewModel.movieData.observeForever(observerWithoutPagedList)

        Mockito.verify(observerWithoutPagedList).onChanged(expected.value)
        Mockito.verify(catalogueRepository).getMovieSelected(id)

        val expectedValue = expected.value
        val actualValue = viewModel.movieData.value

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `getAllFavoriteMovies should be success`() {
        val expected = MutableLiveData<PagedList<MovieEntity>>()
        expected.value = PagedTestDataSources.snapshot(dataDummies)

        `when`(catalogueRepository.getAllFavoriteMovies()).thenReturn(expected)

        viewModel.getAllFavoriteMovies().observeForever(observerWithoutResource)
        Mockito.verify(observerWithoutResource).onChanged(expected.value)
        Mockito.verify(catalogueRepository).getAllFavoriteMovies()

        val expectedValue = expected.value
        val actualValue = viewModel.getAllFavoriteMovies().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun `getAllFavoriteMovies should be success but data is empty`() {
        val expected = MutableLiveData<PagedList<MovieEntity>>()
        expected.value = PagedTestDataSources.snapshot()

        `when`(catalogueRepository.getAllFavoriteMovies()).thenReturn(expected)

        viewModel.getAllFavoriteMovies().observeForever(observerWithoutResource)
        Mockito.verify(observerWithoutResource).onChanged(expected.value)
        Mockito.verify(catalogueRepository).getAllFavoriteMovies()

        val actualValueDataSize = viewModel.getAllFavoriteMovies().value?.size
        assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)
    }

    class PagedTestDataSources private constructor(private val items: List<MovieEntity>) : PositionalDataSource<MovieEntity>() {
        companion object {
            fun snapshot(items: List<MovieEntity> = listOf()): PagedList<MovieEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                        .setNotifyExecutor(Executors.newSingleThreadExecutor())
                        .setFetchExecutor(Executors.newSingleThreadExecutor())
                        .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<MovieEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MovieEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}