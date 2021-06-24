package com.anggasaraya.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity
import com.anggasaraya.moviecatalogue.data.remote.*
import com.anggasaraya.moviecatalogue.data.remote.response.DetailMovieResponse
import com.anggasaraya.moviecatalogue.data.remote.response.DetailTVShowResponse
import com.anggasaraya.moviecatalogue.data.remote.response.ResultsItemTVShow
import com.anggasaraya.moviecatalogue.data.remote.response.ResultsItemMovie

class FakeCatalogueRepository(private val remoteDataSource: RemoteDataSource) : CatalogueDataSource{

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()

        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(moviesResponses: List<ResultsItemMovie?>?) {
                val movieList = ArrayList<MovieEntity>()

                for (response in moviesResponses!!){
                    val movie = MovieEntity(
                            id = response?.id.toString(),
                            dateReleased = response?.releaseDate.toString(),
                            title = response?.originalTitle.toString(),
                            description = if (response?.overview.toString() != "")
                                response?.overview.toString() else "Tidak ada deskripsi",
                            imagePath = response?.posterPath.toString()
                    )
                    movieList.add(movie)
                }

                movieResults.postValue(movieList)
            }
        })
        return movieResults
    }

    override fun getMovieSelected(id: String): LiveData<MovieEntity> {
        val movie = MutableLiveData<MovieEntity>()
        remoteDataSource.getMovieSelected(object : RemoteDataSource.LoadMovieSelectedCallback{
            override fun onMovieSelectedReceived(movieResponse: DetailMovieResponse?) {
                var allGenres = ""
                var isFirst = true
                for (genre in movieResponse?.genres!!){
                    if (isFirst){
                        allGenres += "${genre?.name}"
                        isFirst = false
                        continue
                    }
                    allGenres += ", ${genre?.name}"
                }
                movie.postValue(
                        MovieEntity(
                                id = movieResponse.id.toString(),
                                dateReleased = movieResponse.releaseDate.toString(),
                                title = movieResponse.originalTitle.toString(),
                                genre = allGenres,
                                userScore = movieResponse.voteAverage.toString(),
                                description = if (movieResponse.overview.toString() != "")
                                    movieResponse.overview.toString() else "Tidak ada deskripsi",
                                status = movieResponse.status.toString(),
                                language = movieResponse.spokenLanguages?.get(0)?.englishName.toString(),
                                budget = movieResponse.budget.toString(),
                                income = movieResponse.revenue.toString(),
                                imagePath = movieResponse.posterPath.toString()
                        )
                )
            }
        }, id)
        return movie
    }

    override fun getAllTVShows(): LiveData<List<TVShowEntity>> {
        val tvShowResults = MutableLiveData<List<TVShowEntity>>()

        remoteDataSource.getAllTVShows(object : RemoteDataSource.LoadTVShowsCallback {
            override fun onAllTVShowsReceived(tvShowRespons: List<ResultsItemTVShow?>?) {
                val tvShowList = ArrayList<TVShowEntity>()

                for (response in tvShowRespons!!){
                    val movie = TVShowEntity(
                            id = response?.id.toString(),
                            dateReleased = response?.firstAirDate.toString(),
                            title = response?.originalName.toString(),
                            description = if (response?.overview.toString() != "")
                                response?.overview.toString() else "Tidak ada deskripsi",
                            imagePath = response?.posterPath.toString()
                    )
                    tvShowList.add(movie)
                }

                tvShowResults.postValue(tvShowList)
            }
        })
        return tvShowResults
    }

    override fun getTVShowSelected(id: String): LiveData<TVShowEntity> {
        val tvShow = MutableLiveData<TVShowEntity>()
        remoteDataSource.getTVShowSelected(object : RemoteDataSource.LoadTVShowSelectedCallback{
            override fun onTVShowsSelectedReceived(tvShowResponse: DetailTVShowResponse?) {
                var allGenres = ""
                var isFirst = true
                for (genre in tvShowResponse?.genres!!){
                    if (isFirst){
                        allGenres += "${genre?.name}"
                        isFirst = false
                        continue
                    }
                    allGenres += ", ${genre?.name}"
                }
                tvShow.postValue(
                        TVShowEntity(
                                id = tvShowResponse.id.toString(),
                                dateReleased = tvShowResponse.firstAirDate.toString(),
                                title = tvShowResponse.originalName.toString(),
                                genre = allGenres,
                                userScore = tvShowResponse.voteAverage.toString(),
                                description = if (tvShowResponse.overview.toString() != "")
                                    tvShowResponse.overview.toString() else "Tidak ada deskripsi",
                                status = tvShowResponse.status.toString(),
                                language = tvShowResponse.spokenLanguages?.get(0)?.englishName.toString(),
                                imagePath = tvShowResponse.posterPath.toString()
                        )
                )
            }
        }, id)
        return tvShow
    }
}