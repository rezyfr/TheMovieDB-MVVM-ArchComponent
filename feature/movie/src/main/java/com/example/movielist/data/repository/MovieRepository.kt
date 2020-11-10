package com.example.movielist.data.repository

import com.example.movielist.data.dao.MovieDao
import com.example.movielist.data.model.Movie
import com.example.movielist.data.remote.MovieApi
import com.example.movielist.data.remote.response.MovieDetailResponse
import com.example.movielist.data.remote.response.MovieListResponse
import com.example.movielist.data.remote.response.ReviewResponse
import com.example.movielist.data.remote.response.TrailerResponse

interface MovieRepository {
    suspend fun fetchMovieList(hashMap: HashMap<String, String>): MovieListResponse
    suspend fun getMovieDetail(movieId: Int): MovieDetailResponse
    suspend fun getMovieReviews(page: String, movieId: Int): ReviewResponse
    suspend fun getMovieById(id: String): Movie?
    suspend fun getMovieList(): List<Movie>?
    suspend fun getTrailerDetail(movieId: Int): TrailerResponse?
}

class MovieRepositoryImpl(
    private var apiService: MovieApi,
    private var movieDao: MovieDao
) : MovieRepository {
    override suspend fun fetchMovieList(hashMap: HashMap<String, String>): MovieListResponse {
        return apiService.getDiscoverMovieAsync(hashMap)
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetailResponse {
        return apiService.getMovieDetail(movieId)
    }

    override suspend fun getMovieReviews(page: String, movieId: Int): ReviewResponse {
        return apiService.getReviewList(movieId, page)
    }

    override suspend fun getTrailerDetail(movieId: Int): TrailerResponse? {
        return apiService.getTrailerDetail(movieId)
    }

    override suspend fun getMovieById(id: String): Movie? {
        return movieDao.getMovieById(id)
    }

    override suspend fun getMovieList(): List<Movie>? {
        return movieDao.getMovieList()
    }
}