package com.example.axiatatest.data.repository

import com.example.axiatatest.data.local.dao.GenreDao
import com.example.axiatatest.data.model.Genre
import com.example.axiatatest.data.remote.ApiService
import com.example.axiatatest.data.remote.response.GenreResponse

interface GenreRepository {
    suspend fun fetchGenreList(): GenreResponse
    suspend fun saveGenreToDb(data: List<Genre>)
    suspend fun getGenreList(): List<Genre>?
}

class GenreRepositoryImpl(
    private val apiService: ApiService,
    private val genreDao: GenreDao
) : GenreRepository {
    override suspend fun fetchGenreList(): GenreResponse {
        return apiService.getGenreList()
    }

    override suspend fun saveGenreToDb(data: List<Genre>) {
        genreDao.insertAll(data)
    }

    override suspend fun getGenreList(): List<Genre>? {
        return genreDao.getGenreList()
    }
}