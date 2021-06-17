package com.anggasaraya.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity
import com.anggasaraya.moviecatalogue.data.remote.LoadMovieSelectedCallback
import com.anggasaraya.moviecatalogue.data.remote.LoadMoviesCallback
import com.anggasaraya.moviecatalogue.data.remote.RemoteDataSource
import com.anggasaraya.moviecatalogue.data.remote.response.DetailMovieResponse
import com.anggasaraya.moviecatalogue.data.remote.response.ResultsItem
import com.anggasaraya.moviecatalogue.helper.DataDummy

class CatalogueRepository private constructor(private val remoteDataSource: RemoteDataSource) : CatalogueDataSource{

    companion object{
        @Volatile
        private var instance: CatalogueRepository? = null

        fun getInstance(remoteData: RemoteDataSource): CatalogueRepository =
                instance ?: synchronized(this){
                    instance ?: CatalogueRepository(remoteData)
                }
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getAllMovies(object : LoadMoviesCallback {
            override fun onAllMoviesReceived(moviesResponses: List<ResultsItem>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in moviesResponses){
                    val movie = MovieEntity(
                            id = response.id.toString(),
                            dateReleased = response.releaseDate.toString(),
                            title = response.title.toString(),
                            description = response.overview.toString(),
                            imagePath = response.posterPath.toString()
                    )
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        }, "1")

        return movieResults
    }

    override fun getAllTVShows(): ArrayList<TVShowEntity> {
        return DataDummy.generateDummyTVShows()
    }

    override fun getMovieSelected(id: String): MovieEntity {
        lateinit var movie: MovieEntity
        remoteDataSource.getMovieSelected(object : LoadMovieSelectedCallback{
            override fun onMovieSelectedReceived(movieResponses: DetailMovieResponse) {
                movie = MovieEntity(
                        id = movieResponses.id.toString(),
                        dateReleased = movieResponses.releaseDate.toString(),
                        title = movieResponses.title.toString(),
                        description = movieResponses.overview.toString(),
                        imagePath = movieResponses.posterPath.toString()
                )
            }
        }, id)

        return movie
        /*val movieEntities = DataDummy.generateDummyMovies()
        for (movieEntity in movieEntities) {
            if (movieEntity.id == id) {
                movie = movieEntity
            }
        }
        return movie*/
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