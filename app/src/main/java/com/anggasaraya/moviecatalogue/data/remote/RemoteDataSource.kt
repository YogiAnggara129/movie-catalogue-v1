package com.anggasaraya.moviecatalogue.data.remote

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    fun getAllMovies(): LiveData<ApiResponse<List<ResultsItemMovie?>>> {
        EspressoIdlingResource.increment()
        val moviesResponse = MutableLiveData<ApiResponse<List<ResultsItemMovie?>>>()
        val client = ApiConfig.getApiService().getMovies(
                "db60181fe10f220153af611c6d461e50",
                "id-ID",
                "popularity.desc",
                2019
        )
        client.enqueue(object : Callback<MoviesResponse>{
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                if (response.isSuccessful){
                    moviesResponse.value = ApiResponse.success(response.body()!!.results!!)
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                t.printStackTrace()
                EspressoIdlingResource.decrement()
            }
        })
        return moviesResponse
    }

    fun getMovieSelected(id: String) : LiveData<ApiResponse<DetailMovieResponse?>> {
        EspressoIdlingResource.increment()
        val movieResponse = MutableLiveData<ApiResponse<DetailMovieResponse?>>()
        val client = ApiConfig.getApiService().getDetailMovie(
                id,
                "db60181fe10f220153af611c6d461e50",
                "id-ID"
        )
        client.enqueue(object : Callback<DetailMovieResponse>{
            override fun onResponse(call: Call<DetailMovieResponse>, response: Response<DetailMovieResponse>) {
                if (response.isSuccessful){
                    movieResponse.value = ApiResponse.success(response.body()!!)
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                t.printStackTrace()
                EspressoIdlingResource.decrement()
            }
        })
        return movieResponse
    }

    fun getAllTVShows() : LiveData<ApiResponse<List<ResultsItemTVShow?>>> {
        EspressoIdlingResource.increment()
        val tvShowsRespons = MutableLiveData<ApiResponse<List<ResultsItemTVShow?>>>()
        val client = ApiConfig.getApiService().getTVShows(
                "db60181fe10f220153af611c6d461e50",
                "id-ID",
                "popularity.desc",
                2019
        )
        client.enqueue(object : Callback<TVShowResponse>{
            override fun onResponse(call: Call<TVShowResponse>, response: Response<TVShowResponse>) {
                if (response.isSuccessful){
                    tvShowsRespons.value = ApiResponse.success(response.body()!!.results!!)
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                t.printStackTrace()
                EspressoIdlingResource.decrement()
            }
        })
        return tvShowsRespons
    }

    fun getTVShowSelected(id: String) : LiveData<ApiResponse<DetailTVShowResponse?>> {
        EspressoIdlingResource.increment()
        val tvShowResponse = MutableLiveData<ApiResponse<DetailTVShowResponse?>>()
        val client = ApiConfig.getApiService().getDetailTVShow(
                id,
                "db60181fe10f220153af611c6d461e50",
                "id-ID"
        )
        client.enqueue(object : Callback<DetailTVShowResponse>{
            override fun onResponse(call: Call<DetailTVShowResponse>, response: Response<DetailTVShowResponse>) {
                if (response.isSuccessful){
                    tvShowResponse.value = ApiResponse.success(response.body()!!)
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<DetailTVShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                t.printStackTrace()
                EspressoIdlingResource.decrement()
            }
        })
        return tvShowResponse
    }
}


