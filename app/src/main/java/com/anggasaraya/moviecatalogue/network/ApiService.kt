package com.anggasaraya.moviecatalogue.network

import com.anggasaraya.moviecatalogue.data.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("3/discover/movie")
    fun getMovies(
            @Query("api_key") api_key: String,
            @Query("language") language: String,
            @Query("sort_by") sort_by: String,
            @Query("year") year: Int
    ): Call<MoviesResponse>

    @GET("3/movie/{id}")
    fun getDetailMovie(
            @Path("id") id: String,
            @Query("api_key") api_key: String,
            @Query("language") language: String
    ): Call<DetailMovieResponse>

    @GET("3/discover/tv")
    fun getTVShows(
            @Query("api_key") api_key: String,
            @Query("language") language: String,
            @Query("sort_by") sort_by: String,
            @Query("year") year: Int
    ): Call<TVShowResponse>

    @GET("3/tv/{id}")
    fun getDetailTVShow(
            @Path("id") id: String,
            @Query("api_key") api_key: String,
            @Query("language") language: String
    ): Call<DetailTVShowResponse>

}