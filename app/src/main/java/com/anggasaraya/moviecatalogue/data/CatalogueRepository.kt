package com.anggasaraya.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.anggasaraya.moviecatalogue.data.local.LocalDataSource
import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity
import com.anggasaraya.moviecatalogue.data.remote.*
import com.anggasaraya.moviecatalogue.data.remote.response.DetailMovieResponse
import com.anggasaraya.moviecatalogue.data.remote.response.DetailTVShowResponse
import com.anggasaraya.moviecatalogue.data.remote.response.ResultsItemTVShow
import com.anggasaraya.moviecatalogue.data.remote.response.ResultsItemMovie
import com.anggasaraya.moviecatalogue.helper.AppExecutors
import com.anggasaraya.moviecatalogue.vo.Resource

class CatalogueRepository private constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors) : CatalogueDataSource{

    companion object{
        @Volatile
        private var instance: CatalogueRepository? = null

        fun getInstance(
                remoteData: RemoteDataSource,
                localData: LocalDataSource,
                appExecutors: AppExecutors): CatalogueRepository =
                instance ?: synchronized(this){
                    instance ?: CatalogueRepository(remoteData, localData, appExecutors)
                }
    }

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {

        return object : NetworkBoundResource<PagedList<MovieEntity>, List<ResultsItemMovie?>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ResultsItemMovie?>>> =
                remoteDataSource.getAllMovies()

            public override fun saveCallResult(data: List<ResultsItemMovie?>) {
                val movieList = ArrayList<MovieEntity>()

                for (d in data){
                    val movie = MovieEntity(
                        id = d?.id.toString(),
                        dateReleased = d?.releaseDate.toString(),
                        title = d?.originalTitle.toString(),
                        description = if (d?.overview.toString() != "")
                            d?.overview.toString() else "Tidak ada deskripsi",
                        imagePath = d?.posterPath.toString()
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getMovieSelected(id: String): LiveData<Resource<MovieEntity>> {

        return object : NetworkBoundResource<MovieEntity, DetailMovieResponse?>(appExecutors) {
            public override fun loadFromDB(): LiveData<MovieEntity> =
                    localDataSource.getMovieSelected(id)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data?.userScore == null

            public override fun createCall(): LiveData<ApiResponse<DetailMovieResponse?>> =
                    remoteDataSource.getMovieSelected(id)

            public override fun saveCallResult(data: DetailMovieResponse?) {
                var allGenres = ""
                var isFirst = true
                for (genre in data!!.genres!!){
                    if (isFirst){
                        allGenres += "${genre?.name}"
                        isFirst = false
                        continue
                    }
                    allGenres += ", ${genre?.name}"
                }
                val movie =
                        MovieEntity(
                                id = data.id.toString(),
                                dateReleased = data.releaseDate.toString(),
                                title = data.originalTitle.toString(),
                                genre = allGenres,
                                userScore = data.voteAverage.toString(),
                                description = if (data.overview.toString() != "")
                                    data.overview.toString() else "Tidak ada deskripsi",
                                status = data.status.toString(),
                                language = data.spokenLanguages?.get(0)?.englishName.toString(),
                                budget = data.budget.toString(),
                                income = data.revenue.toString(),
                                imagePath = data.posterPath.toString()
                        )
                localDataSource.updateMovieDetail(movie)
            }
        }.asLiveData()
    }

    override fun getAllFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
        return LivePagedListBuilder(localDataSource.getAllFavoriteMovies(), config).build()
    }

    override fun setMovieFavorite(movie: MovieEntity, state: Boolean) =
            appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movie, state) }

    override fun getAllTVShows(): LiveData<Resource<PagedList<TVShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TVShowEntity>, List<ResultsItemTVShow?>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TVShowEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                return LivePagedListBuilder(localDataSource.getAllTVShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TVShowEntity>?): Boolean =
                    data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ResultsItemTVShow?>>> =
                    remoteDataSource.getAllTVShows()

            public override fun saveCallResult(data: List<ResultsItemTVShow?>) {
                val tvShowList = ArrayList<TVShowEntity>()

                for (d in data){
                    val tvShow = TVShowEntity(
                            id = d?.id.toString(),
                            dateReleased = d?.firstAirDate.toString(),
                            title = d?.originalName.toString(),
                            description = if (d?.overview.toString() != "")
                                d?.overview.toString() else "Tidak ada deskripsi",
                            imagePath = d?.posterPath.toString()
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTVShow(tvShowList)
            }
        }.asLiveData()
    }

    override fun getTVShowSelected(id: String): LiveData<Resource<TVShowEntity>> {
        return object : NetworkBoundResource<TVShowEntity, DetailTVShowResponse?>(appExecutors) {
            public override fun loadFromDB(): LiveData<TVShowEntity> =
                    localDataSource.getTVShowSelected(id)

            override fun shouldFetch(data: TVShowEntity?): Boolean =
                    data?.userScore == null

            public override fun createCall(): LiveData<ApiResponse<DetailTVShowResponse?>> =
                    remoteDataSource.getTVShowSelected(id)

            public override fun saveCallResult(data: DetailTVShowResponse?) {
                var allGenres = ""
                var isFirst = true
                for (genre in data!!.genres!!){
                    if (isFirst){
                        allGenres += "${genre?.name}"
                        isFirst = false
                        continue
                    }
                    allGenres += ", ${genre?.name}"
                }
                val tvShow =
                        TVShowEntity(
                                id = data.id.toString(),
                                dateReleased = data.firstAirDate.toString(),
                                title = data.originalName.toString(),
                                genre = allGenres,
                                userScore = data.voteAverage.toString(),
                                description = if (data.overview.toString() != "")
                                    data.overview.toString() else "Tidak ada deskripsi",
                                status = data.status.toString(),
                                language = data.spokenLanguages?.get(0)?.englishName.toString(),
                                imagePath = data.posterPath.toString()
                        )
                localDataSource.updateTVShowDetail(tvShow)
            }
        }.asLiveData()
    }

    override fun getAllFavoriteTVShows(): LiveData<PagedList<TVShowEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
        return LivePagedListBuilder(localDataSource.getAllFavoriteTVShows(), config).build()
    }

    override fun setTVShowFavorite(tvShow: TVShowEntity, state: Boolean) =
            appExecutors.diskIO().execute { localDataSource.setTVShowFavorite(tvShow, state) }
}