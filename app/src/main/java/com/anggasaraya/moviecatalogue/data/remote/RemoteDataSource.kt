package com.anggasaraya.moviecatalogue.data.remote

import android.content.ContentValues.TAG
import android.util.Log
import com.anggasaraya.moviecatalogue.data.remote.response.*
import com.anggasaraya.moviecatalogue.helper.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    @Volatile
    private var instance: RemoteDataSource? = null

    fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }

    fun getAllMovies(callback: LoadMoviesCallback, page: String) {
        EspressoIdlingResource.increment()
        var moviesResponses: List<ResultsItemMovie?>?
        val client = ApiConfig.getApiService().getPopularMovies(
                "db60181fe10f220153af611c6d461e50",
                page,
                "id-ID",
                "ID"
        )
        client.enqueue(object : Callback<PopularMoviesResponse>{
            override fun onResponse(call: Call<PopularMoviesResponse>, response: Response<PopularMoviesResponse>) {
                if (response.isSuccessful){
                    moviesResponses = response.body()?.results
                    Log.i(TAG, "datanya: ${moviesResponses.toString()}")
                    callback.onAllMoviesReceived(moviesResponses)
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<PopularMoviesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                t.printStackTrace()
            }
        })
    }

    fun getMovieSelected(callback: LoadMovieSelectedCallback, id: String) {
        EspressoIdlingResource.increment()
        var movieResponse: DetailMovieResponse?
        val client = ApiConfig.getApiService().getDetailMovie(
                id,
                "db60181fe10f220153af611c6d461e50",
                "id-ID",
                "ID"
        )
        client.enqueue(object : Callback<DetailMovieResponse>{
            override fun onResponse(call: Call<DetailMovieResponse>, response: Response<DetailMovieResponse>) {
                if (response.isSuccessful){
                    movieResponse = response.body()
                    Log.i(TAG, "datanya: ${movieResponse.toString()}")
                    callback.onMovieSelectedReceived(movieResponse)
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getAllTVShows(callback: LoadTVShowsCallback, page: String) {
        EspressoIdlingResource.increment()
        var moviesResponses: List<ResultsItem?>?
        val client = ApiConfig.getApiService().getPopularTVShows(
                "db60181fe10f220153af611c6d461e50",
                page,
                "id-ID",
                "ID"
        )
        client.enqueue(object : Callback<PopularTVShowResponse>{
            override fun onResponse(call: Call<PopularTVShowResponse>, response: Response<PopularTVShowResponse>) {
                if (response.isSuccessful){
                    moviesResponses = response.body()?.results
                    Log.i(TAG, "datanya: ${moviesResponses.toString()}")
                    callback.onAllTVShowsReceived(moviesResponses)
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<PopularTVShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                t.printStackTrace()
            }
        })
    }

    fun getTVShowSelected(callback: LoadTVShowSelectedCallback, id: String) {
        EspressoIdlingResource.increment()
        var tvShowResponse: DetailTVShowResponse?
        val client = ApiConfig.getApiService().getDetailTVShow(
                id,
                "db60181fe10f220153af611c6d461e50",
                "id-ID",
                "ID"
        )
        client.enqueue(object : Callback<DetailTVShowResponse>{
            override fun onResponse(call: Call<DetailTVShowResponse>, response: Response<DetailTVShowResponse>) {
                if (response.isSuccessful){
                    tvShowResponse = response.body()
                    Log.i(TAG, "datanya: ${tvShowResponse.toString()}")
                    callback.onTVShowsSelectedReceived(tvShowResponse)
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<DetailTVShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
}

interface LoadMoviesCallback {
    fun onAllMoviesReceived(moviesResponses: List<ResultsItemMovie?>?)
}

interface LoadMovieSelectedCallback {
    fun onMovieSelectedReceived(movieResponse: DetailMovieResponse?)
}

interface LoadTVShowsCallback {
    fun onAllTVShowsReceived(tvShowResponses: List<ResultsItem?>?)
}

interface LoadTVShowSelectedCallback {
    fun onTVShowsSelectedReceived(tvShowResponse: DetailTVShowResponse?)
}
