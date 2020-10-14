package com.example.axiatatest.data.repository

import com.example.axiatatest.data.local.dao.MovieDao
import com.example.axiatatest.data.model.Movie
import com.example.axiatatest.data.remote.ApiService
import com.example.axiatatest.data.remote.response.MovieDetailResponse
import com.example.axiatatest.data.remote.response.MovieListResponse
import com.example.axiatatest.data.remote.response.ReviewResponse

interface MovieRepository {
    suspend fun fetchMovieList(hashMap: HashMap<String, String>): MovieListResponse
    suspend fun getMovieDetail(movieId: Int): MovieDetailResponse
    suspend fun getMovieReviews(page: String, movieId: Int): ReviewResponse
    suspend fun insertMovieToDb(list: List<Movie>)
    suspend fun insertMovieToDb(movie: Movie)
    suspend fun getMovieById(id: String): Movie?
    suspend fun getMovieList(): List<Movie>?
}

class MovieRepositoryImpl(
    private var apiService: ApiService,
    private var movieDao: MovieDao
): MovieRepository{
    override suspend fun fetchMovieList(hashMap: HashMap<String, String>): MovieListResponse {
        return apiService.getDiscoverMovieAsync(hashMap)
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetailResponse {
        return apiService.getMovieDetail(movieId)
    }

    override suspend fun getMovieReviews(page: String, movieId: Int): ReviewResponse {
        return apiService.getReviewList(movieId, page)
    }

    override suspend fun insertMovieToDb(list: List<Movie>) {
        movieDao.insertAll(list)
    }

    override suspend fun insertMovieToDb(movie: Movie) {
        movieDao.insert(movie)
    }

    override suspend fun getMovieById(id: String): Movie? {
        return movieDao.getMovieById(id)
    }

    override suspend fun getMovieList(): List<Movie>? {
        return movieDao.getMovieList()
    }
}