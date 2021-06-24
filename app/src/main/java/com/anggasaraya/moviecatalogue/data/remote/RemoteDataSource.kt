package com.anggasaraya.moviecatalogue.data.remote

import android.content.ContentValues.TAG
import android.util.Log
import com.anggasaraya.moviecatalogue.data.remote.response.*
import com.anggasaraya.moviecatalogue.helper.EspressoIdlingResource
import com.anggasaraya.moviecatalogue.network.ApiConfig
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

    fun getAllMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        var moviesResponses: List<ResultsItemMovie?>?
        val client = ApiConfig.getApiService().getMovies(
                "db60181fe10f220153af611c6d461e50",
                "id-ID",
                "popularity.desc",
                2019
        )
        client.enqueue(object : Callback<MoviesResponse>{
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                if (response.isSuccessful){
                    moviesResponses = response.body()?.results
                    Log.i(TAG, "datanya: ${moviesResponses.toString()}")
                    callback.onAllMoviesReceived(moviesResponses)
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                t.printStackTrace()
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getMovieSelected(callback: LoadMovieSelectedCallback, id: String) {
        EspressoIdlingResource.increment()
        var movieResponse: DetailMovieResponse?
        val client = ApiConfig.getApiService().getDetailMovie(
                id,
                "db60181fe10f220153af611c6d461e50",
                "id-ID"
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
                t.printStackTrace()
                EspressoIdlingResource.decrement()
            }

        })
    }

    fun getAllTVShows(callback: LoadTVShowsCallback) {
        EspressoIdlingResource.increment()
        var moviesRespons: List<ResultsItemTVShow?>?
        val client = ApiConfig.getApiService().getTVShows(
                "db60181fe10f220153af611c6d461e50",
                "id-ID",
                "popularity.desc",
                2019
        )
        client.enqueue(object : Callback<TVShowResponse>{
            override fun onResponse(call: Call<TVShowResponse>, response: Response<TVShowResponse>) {
                if (response.isSuccessful){
                    moviesRespons = response.body()?.results
                    Log.i(TAG, "datanya: ${moviesRespons.toString()}")
                    callback.onAllTVShowsReceived(moviesRespons)
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                t.printStackTrace()
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getTVShowSelected(callback: LoadTVShowSelectedCallback, id: String) {
        EspressoIdlingResource.increment()
        var tvShowResponse: DetailTVShowResponse?
        val client = ApiConfig.getApiService().getDetailTVShow(
                id,
                "db60181fe10f220153af611c6d461e50",
                "id-ID"
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
                t.printStackTrace()
                EspressoIdlingResource.decrement()
            }

        })
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(moviesResponses: List<ResultsItemMovie?>?)
    }

    interface LoadMovieSelectedCallback {
        fun onMovieSelectedReceived(movieResponse: DetailMovieResponse?)
    }

    interface LoadTVShowsCallback {
        fun onAllTVShowsReceived(tvShowRespons: List<ResultsItemTVShow?>?)
    }

    interface LoadTVShowSelectedCallback {
        fun onTVShowsSelectedReceived(tvShowResponse: DetailTVShowResponse?)
    }
}


