package com.anggasaraya.moviecatalogue.data.remote

import com.anggasaraya.moviecatalogue.data.remote.response.DetailMovieResponse
import com.anggasaraya.moviecatalogue.data.remote.response.ResultsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/popular")
    fun getPopularMovies(
            @Query("api_key") api_key: String,
            @Query("page") page: String,
            @Query("language") language: String,
            @Query("region") region: String
            /*@Query("api_key") api_key: String = "db60181fe10f220153af611c6d461e50",
            @Query("page") page: String,
            @Query("language") language: String = "id-ID",
            @Query("region") region: String = "ID"*/
    ): Call<List<ResultsItem>>

    @GET("3/movie/{id}")
    fun getDetailMovie(
            @Query("api_key") api_key: String,
            @Query("language") language: String,
            @Path("id") id: String
            /*@Query("api_key") api_key: String = "db60181fe10f220153af611c6d461e50",
            @Query("language") language: String = "id-ID",
            @Path("id") id: String*/
    ): Call<DetailMovieResponse>

}