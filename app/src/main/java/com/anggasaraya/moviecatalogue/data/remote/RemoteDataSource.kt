package com.anggasaraya.moviecatalogue.data.remote

import android.content.ContentValues.TAG
import android.util.Log
import com.anggasaraya.moviecatalogue.data.remote.response.DetailMovieResponse
import com.anggasaraya.moviecatalogue.data.remote.response.ResultsItem
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
        var moviesResponses: List<ResultsItem>? = null
        val client = ApiConfig.getApiService().getPopularMovies(
                "db60181fe10f220153af611c6d461e50",
                page,
                "id-ID",
                "ID"
        )
        client.enqueue(object : Callback<List<ResultsItem>>{
            override fun onResponse(call: Call<List<ResultsItem>>, response: Response<List<ResultsItem>>) {
                if (response.isSuccessful){
                    moviesResponses = response.body()
                }
            }
            override fun onFailure(call: Call<List<ResultsItem>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                Log.e(TAG, moviesResponses.toString())
            }

        })
        moviesResponses?.let { callback.onAllMoviesReceived(it) }
    }

    fun getMovieSelected(callback: LoadMovieSelectedCallback, id: String) {
        var movieResponses: DetailMovieResponse? = null
        val client = ApiConfig.getApiService().getDetailMovie(
                "db60181fe10f220153af611c6d461e50",
                "ID",
                id
        )
        client.enqueue(object : Callback<DetailMovieResponse>{
            override fun onResponse(call: Call<DetailMovieResponse>, response: Response<DetailMovieResponse>) {
                if (response.isSuccessful){
                    movieResponses = response.body()
                }
            }
            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
        movieResponses?.let { callback.onMovieSelectedReceived(it) }
    }
}

interface LoadMovieSelectedCallback {
    fun onMovieSelectedReceived(movieResponses: DetailMovieResponse)
}

interface LoadMoviesCallback {
    fun onAllMoviesReceived(moviesResponses: List<ResultsItem>)
}
