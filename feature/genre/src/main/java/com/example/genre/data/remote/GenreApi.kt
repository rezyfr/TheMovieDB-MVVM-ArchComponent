package com.example.genre.data.remote

import com.example.genre.data.remote.response.GenreResponse
import retrofit2.http.GET

interface GenreApi {
    @GET("3/genre/movie/list")
    suspend fun getGenreList(): GenreResponse
}