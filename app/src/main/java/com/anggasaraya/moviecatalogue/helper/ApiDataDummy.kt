package com.anggasaraya.moviecatalogue.helper

import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity
import com.anggasaraya.moviecatalogue.data.remote.response.*
import com.anggasaraya.moviecatalogue.network.ApiConfig
import retrofit2.Response

object ApiDataDummy {
    fun generateRemoteDummyMovies(): List<ResultsItemMovie?> {
        var moviesResponse = listOf<ResultsItemMovie?>()
        val client = ApiConfig.getApiService().getMovies(
                "db60181fe10f220153af611c6d461e50",
                "id-ID",
                "popularity.desc",
                2019
        )
        try {
            val response: Response<MoviesResponse> = client.execute()
            moviesResponse = response.body()!!.results!!
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return moviesResponse
    }

    fun generateRemoteDetailMovie(id: String): DetailMovieResponse{
        var movieResponse = DetailMovieResponse()
        val client = ApiConfig.getApiService().getDetailMovie(
                id,
                "db60181fe10f220153af611c6d461e50",
                "id-ID"
        )

        try {
            val response: Response<DetailMovieResponse> = client.execute()
            movieResponse = response.body()!!
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return movieResponse
    }

    fun generateDummyMovies(): List<MovieEntity> {
        val movieList =  ArrayList<MovieEntity>()
        val moviesResponse = generateRemoteDummyMovies()
        for (d in moviesResponse) {
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
        return movieList
    }

    fun generateDummyDetailMovie(id: String, favorited: Boolean = false): MovieEntity{
        val movieResponse = generateRemoteDetailMovie(id)
        var allGenres = ""
        var isFirst = true
        for (genre in movieResponse.genres!!){
            if (isFirst){
                allGenres += "${genre?.name}"
                isFirst = false
                continue
            }
            allGenres += ", ${genre?.name}"
        }
        return MovieEntity(
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
                imagePath = movieResponse.posterPath.toString(),
                isFavorite = favorited
        )
    }

    fun generateRemoteDummyTVShows(): List<ResultsItemTVShow?> {
        var tvShowsResponse =  listOf<ResultsItemTVShow?>()
        val client = ApiConfig.getApiService().getTVShows(
                "db60181fe10f220153af611c6d461e50",
                "id-ID",
                "popularity.desc",
                2019
        )
        try {
            val response: Response<TVShowResponse> = client.execute()
            tvShowsResponse = response.body()!!.results!!
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return tvShowsResponse
    }

    fun generateRemoteDummyDetailTVShow(id: String): DetailTVShowResponse{
        var tvShowResponse = DetailTVShowResponse()
        val client = ApiConfig.getApiService().getDetailTVShow(
                id,
                "db60181fe10f220153af611c6d461e50",
                "id-ID"
        )

        try {
            val response: Response<DetailTVShowResponse> = client.execute()
            tvShowResponse = response.body()!!
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return tvShowResponse
    }

    fun generateDummyTVShows(): List<TVShowEntity> {
        val tvShowList = ArrayList<TVShowEntity>()
        val tvShowsResponse = generateRemoteDummyTVShows()
        for (d in tvShowsResponse) {
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
        return tvShowList
    }

    fun generateDummyDetailTVShow(id: String, favorited: Boolean = false): TVShowEntity{
        val tvShowResponse = generateRemoteDummyDetailTVShow(id)
        var allGenres = ""
        var isFirst = true
        for (genre in tvShowResponse.genres!!){
            if (isFirst){
                allGenres += "${genre?.name}"
                isFirst = false
                continue
            }
            allGenres += ", ${genre?.name}"
        }
        return TVShowEntity(
            id = tvShowResponse.id.toString(),
            dateReleased = tvShowResponse.firstAirDate.toString(),
            title = tvShowResponse.originalName.toString(),
            genre = allGenres,
            userScore = tvShowResponse.voteAverage.toString(),
            description = if (tvShowResponse.overview.toString() != "")
                tvShowResponse.overview.toString() else "Tidak ada deskripsi",
            status = tvShowResponse.status.toString(),
            language = tvShowResponse.spokenLanguages?.get(0)?.englishName.toString(),
            imagePath = tvShowResponse.posterPath.toString(),
            isFavorite = favorited
        )
    }
}