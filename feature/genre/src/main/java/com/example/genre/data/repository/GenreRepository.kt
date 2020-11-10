package com.example.genre.data.repository

import com.example.genre.data.dao.GenreDao
import com.example.genre.data.model.Genre
import com.example.genre.data.remote.GenreApi
import com.example.genre.data.remote.response.GenreResponse


interface GenreRepository {
    suspend fun fetchGenreList(): GenreResponse
    suspend fun saveGenreToDb(data: List<Genre>)
    suspend fun getGenreList(): List<Genre>?
}

class GenreRepositoryImpl(
    private val apiService: GenreApi,
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