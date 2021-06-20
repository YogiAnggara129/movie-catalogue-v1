package com.anggasaraya.moviecatalogue.data.remote

import com.anggasaraya.moviecatalogue.data.remote.response.*
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
    ): Call<PopularMoviesResponse>

    @GET("3/movie/{id}")
    fun getDetailMovie(
            @Path("id") id: String,
            @Query("api_key") api_key: String,
            @Query("language") language: String,
            @Query("region") region: String
    ): Call<DetailMovieResponse>

    @GET("3/tv/popular")
    fun getPopularTVShows(
            @Query("api_key") api_key: String,
            @Query("page") page: String,
            @Query("language") language: String,
            @Query("region") region: String
    ): Call<PopularTVShowResponse>

    @GET("3/tv/{id}")
    fun getDetailTVShow(
            @Path("id") id: String,
            @Query("api_key") api_key: String,
            @Query("language") language: String,
            @Query("region") region: String
    ): Call<DetailTVShowResponse>

}