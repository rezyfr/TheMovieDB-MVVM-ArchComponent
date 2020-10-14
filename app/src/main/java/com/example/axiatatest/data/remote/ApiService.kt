package com.example.axiatatest.data.remote

import com.example.axiatatest.data.constants.ApiParams
import com.example.axiatatest.data.remote.response.GenreResponse
import com.example.axiatatest.data.remote.response.MovieDetailResponse
import com.example.axiatatest.data.remote.response.MovieListResponse
import com.example.axiatatest.data.remote.response.ReviewResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {
    @GET("3/discover/movie")
    suspend fun getDiscoverMovieAsync(
        @QueryMap hashMap: HashMap<String, String> = HashMap()
    ): MovieListResponse

    @GET("3/genre/movie/list")
    suspend fun getGenreList(): GenreResponse

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movie_id: Int
    ): MovieDetailResponse

    @GET("3/movie/{movie_id}/reviews")
    suspend fun getReviewList(
        @Path("movie_id") movie_id: Int,
        @Query(ApiParams.PAGE) page: String
    ): ReviewResponse
}