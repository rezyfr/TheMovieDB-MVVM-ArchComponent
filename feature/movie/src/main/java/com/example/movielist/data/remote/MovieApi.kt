package com.example.movielist.data.remote

import com.example.movielist.data.remote.response.MovieDetailResponse
import com.example.movielist.data.remote.response.MovieListResponse
import com.example.movielist.data.remote.response.ReviewResponse
import com.example.movielist.data.remote.response.TrailerResponse
import com.example.network.constants.ApiParams
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MovieApi {
    @GET("3/discover/movie")
    suspend fun getDiscoverMovieAsync(
        @QueryMap hashMap: HashMap<String, String> = HashMap()
    ): MovieListResponse

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movie_id: Int
    ): MovieDetailResponse

    @GET("3/movie/{movie_id}/reviews")
    suspend fun getReviewList(
        @Path("movie_id") movie_id: Int,
        @Query(ApiParams.PAGE) page: String
    ): ReviewResponse

    @GET("3/movie/{movie_id}/videos")
    suspend fun getTrailerDetail(
        @Path("movie_id") movie_id: Int
    ): TrailerResponse
}